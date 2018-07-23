package com.yourui.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.Message;
import com.yourui.web.common.RespMessage;
import com.yourui.web.common.RulesPlus;
import com.yourui.web.config.Config;
import com.yourui.web.model.*;
import com.yourui.web.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 规则接口
 * @author YI
 * @date 2018-6-20 10:48:58
 */
@Controller
@RequestMapping("/rules")
public class RulesController {
    private static final Logger logger = LoggerFactory.getLogger(RulesController.class);

    @Autowired
    RulesService rulesService;
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    CommonService commonService;

    /**
     * 获取所有游戏有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage fidnAllGatewayAddress(){
        RespMessage respMessage = new RespMessage();
        List<RulesPlus> list = new ArrayList<>();

        List<Rules> rulesList = rulesService.fidnAll();

        for (Rules rules: rulesList) {
            RulesPlus item = new RulesPlus();
            BeanUtil.copyProperties(rules, item);
            Long ruleGroupId = item.getRuleGroupId();
            RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(ruleGroupId);
            item.setRuleGroupName(ruleGroup.getRuleGroupName());
            list.add(item);
        }

        respMessage.setData(list);

        return respMessage;
    }

    /**
     * 查看游戏详细数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detail(Long id) {
        ModelAndView modelAndView = new ModelAndView("script/code");
        RespMessage respMessage = new RespMessage();

        Rules rules = rulesService.selectByPrimaryKey(id);
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(rules.getRuleGroupId());

        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);
        List<GatewayAddress> gatewayAddressesList = gatewayAddressService.selectByIds(ids);

        StringBuffer buffer = new StringBuffer();
        gatewayAddressesList.forEach(item-> buffer.append(item.getGatewayAddressName() + "\r\n"));

        respMessage.setData(buffer.toString());

        modelAndView.addObject("message", respMessage);

        return modelAndView;
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage echo(Long id){
        RespMessage respMessage = new RespMessage();
        RulesPlus item = new RulesPlus();

        Rules rules = rulesService.selectByPrimaryKey(id);
        BeanUtil.copyProperties(rules, item);
        Long ruleGroupId = item.getRuleGroupId();
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(ruleGroupId);
        item.setRuleGroupName(ruleGroup.getRuleGroupName());

        respMessage.setData(item);

        return respMessage;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delID", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage delID(Long id){
        RespMessage respMessage = new RespMessage();
        if (id == null){
            respMessage.setMsg("id为空,更新数据失败!");
            respMessage.setCode(-1);

            return respMessage;
        }

        Rules rules = rulesService.selectByPrimaryKey(id);

        // 获取规则组
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(rules.getRuleGroupId());

        // 获取规则组下的规则
        Message message = commonService.findByRuleGroupName(ruleGroup.getRuleGroupName(), rules);

        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

        // 获取规则组下的所有网关
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(ids);

        try {
            // 把网关ip和游戏ip发送到防御网关
            for (GatewayAddress item : gatewayAddresses) {
                // 把网关外网ip和游戏ip发送到防御网关
                HttpUtil.createPost(Config.ADDRESS_HTTP + item.getOutsideNetworkIp() + Config.ADDRESS_PORT)
                        .charset(CharsetUtil.UTF_8)
                        .timeout(2000)
                        .setEncodeUrl(false)
                        .body(JSONUtil.toJsonPrettyStr(message))
                        .execute();
            }
        }catch (Exception e){
            respMessage.setCode(-1);
            respMessage.setData(e.getCause().toString());
            logger.info("规则删除：" + e.getCause());
            e.printStackTrace();
        }

        // 全服通知成功在再执行更新操作
        if (respMessage.getCode() == 0){
            rulesService.updateById(id);
        }

        return respMessage;
    }

    /**
     * 保存规则信息
     * @param rules
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespMessage save(@RequestBody Rules rules){
        RespMessage respMessage = new RespMessage();
        if (rules == null){
            respMessage.setMsg("请先填写数据,更新数据失败!");
            respMessage.setCode(-1);

            return respMessage;
        }

        if (rules.getId() == null) {
            List<Rules> fidnPort = rulesService.fidnPort(rules.getToPort());

            if (fidnPort != null && fidnPort.size() > 0) {
                respMessage.setMsg("该端口号已经使用,请选择其他端口号!");
                respMessage.setCode(-1);

                return respMessage;
            }

//            RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(rules.getRuleGroupId());
//            rules.setRuleGroupId(ruleGroup.getId());

            rulesService.insertSelective(rules);
        }else if (rules.getId() > 0){
            List<Rules> fidnPort = rulesService.fidnDifferentPort(rules);
            if (fidnPort.size() != 0){
                respMessage.setMsg("修改的端口已存在!");
                respMessage.setCode(-1);

                return respMessage;
            }

            rulesService.updateByPrimaryKeySelective(rules);
        }else {
            respMessage.setMsg("未知错误!");
            respMessage.setCode(-1);
        }

        return respMessage;
    }

    /**
     * 公共数据
     * @return
     */
    @RequestMapping("/fidnNetGroupGateway")
    @ResponseBody
    public RespMessage fidnNetGroupGateway(){
        RespMessage respMessage = new RespMessage();
        HashMap<String, Object> map = new HashMap<>(16);

        List<GatewayAddress> gatewayAddresses = gatewayAddressService.fidnAll();
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();
        List<UserGroup> userGroups = userGroupService.fidnAll();

        // 获取剩余可用网关
        List<GatewayAddress> usableGatewayAddresses = commonService.usableGatewayAddresses();

        map.put("gatewayAddresses", gatewayAddresses);
        map.put("ruleGroups", ruleGroups);
        map.put("userGroups", userGroups);
        map.put("usableGatewayAddresses", usableGatewayAddresses);

        respMessage.setData(map);

        return respMessage;
    }
}
