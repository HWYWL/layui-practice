package com.yourui.web.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.RespMessage;
import com.yourui.web.config.Config;
import com.yourui.web.model.*;
import com.yourui.web.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 规则组接口
 * @author YI
 * @date 2018-6-20 09:16:41
 */
@Controller
@RequestMapping("/ruleGroup")
public class RuleGroupController {
    private static final Logger logger = LoggerFactory.getLogger(RuleGroupController.class);

    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    RulesService rulesService;
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    CommonService commonService;

    /**
     * 获取所有组信息有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage fidnAllGatewayAddress(){
        RespMessage respMessage = new RespMessage();
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();
        RuleGroup[] scriptInfos = new RuleGroup[ruleGroups.size()];
        RuleGroup[] infos = ruleGroups.toArray(scriptInfos);

        respMessage.setCount(ruleGroups.size());
        respMessage.setData(infos);

        return respMessage;
    }

    /**
     * 查看规则组包含的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detail(Long id) {
        ModelAndView modelAndView = new ModelAndView("script/code");
        RespMessage respMessage = new RespMessage();

        if (id == null){
            respMessage.setMsg("id为空,更新数据失败!");
            respMessage.setCode(-1);
            modelAndView.addObject("message", respMessage);

            return modelAndView;
        }

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

        if (ids == null || ids.size() == 0){
            respMessage.setMsg("此规则组没有关联网关!");
            respMessage.setData("此规则组没有关联网关!");
            modelAndView.addObject("message", respMessage);

            return modelAndView;
        }

        List<GatewayAddress> gatewayAddress = gatewayAddressService.selectByIds(ids);
        StringBuffer buffer = new StringBuffer();
        gatewayAddress.forEach(item-> buffer.append(item.getGatewayAddressName() + "\r\n"));

        List<Rules> rules = rulesService.selectByRuleGroupId(id);

        for (int i = 0; i < rules.size(); i++) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("fromPort", rules.get(i).getFromPort());
            map.put("toPort", rules.get(i).getToPort());
            map.put("toIp", rules.get(i).getToIp());
            map.put("规则名称", rules.get(i).getRuleName());
            map.put("协议", rules.get(i).getAgreement());
            buffer.append("节点"+ i +":" + map + "\r\n");
        }

        respMessage.setData(buffer.toString());

        modelAndView.addObject("message", respMessage);

        return modelAndView;
    }

    /**
     * 编辑规则组
     * @param id 规则组id
     * @return
     */
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage echo(Long id) {
        RespMessage respMessage = new RespMessage();
        Map<String, Object> map = new HashMap<>(16);

        if (id == null){
            respMessage.setMsg("id为空,查询数据失败!");
            respMessage.setCode(-1);
            logger.info("id为空,查询数据失败!");
            return respMessage;
        }

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);

        // 获得该规则组使用的网关
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(ids);

        map.put("ruleGroup", ruleGroup);
        map.put("gatewayAddresses", gatewayAddresses);

        respMessage.setData(map);

        return respMessage;
    }

    /**
     * 用户组查询可用网关
     * @param id 规则组id
     * @return
     */
    @RequestMapping(value = "/detailGatewayAddress", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage detailGatewayAddress(Long id) {
        RespMessage respMessage = new RespMessage();

        if (id == null){
            respMessage.setMsg("id为空,无可用网关!");
            respMessage.setCode(-1);
            logger.info("id为空,无可用网关!");
            return respMessage;
        }

        List<GatewayAddress> gatewayAddresses = commonService.detailGatewayAddress(id);

        respMessage.setData(gatewayAddresses);

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

        List<Rules> rules = rulesService.selectByRuleGroupId(id);
        if (rules == null || rules.size() == 0){
            ruleGroupService.updateById(id);
        }else {
            respMessage.setMsg("该规则组还包含规则，不能删除!");
            respMessage.setCode(-1);
            logger.info("该规则组还包含规则，不能删除!");

            return respMessage;
        }

        return respMessage;
    }

    /**
     * 执行规则组包含网关
     * @param id
     * @return
     */
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage execute(Long id) {
        RespMessage respMessage = new RespMessage();

        // 获取此规则组的网关地址
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray gatewayAddressIdsArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> longs = JSONUtil.toList(gatewayAddressIdsArray, Long.class);
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(longs);

        // 获取规则组对应的规则
        List<Rules> rules = rulesService.selectByRuleGroupId(id);

        List<String> gatewayIpList = new ArrayList<>();

        gatewayAddresses.forEach(item-> gatewayIpList.add(item.getOutsideNetworkIp()));

        Map<String, Object> map = new HashMap<>(16);
        map.put("gatewayIp", gatewayIpList);
        map.put("rules", rules);

        try {
            // 把网关ip和游戏ip发送到防御网关
            gatewayAddresses.forEach(item->
                HttpUtil.createPost(Config.ADDRESS_HTTP + item.getOutsideNetworkIp() + Config.ADDRESS_PORT)
                    .charset(CharsetUtil.UTF_8)
                    .timeout(2000)
                    .setEncodeUrl(false)
                    .body(JSONUtil.toJsonPrettyStr(map))
                    .execute());
        }catch (Exception e){
            respMessage.setCode(-1);
            respMessage.setData(e.getCause().toString());
            logger.info("把网关ip和游戏ip发送到防御网关:" + e.getCause());
            e.printStackTrace();
        }

        return respMessage;
    }

    /**
     * 保存规则组信息
     * @param ruleGroup
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage save(String ruleGroup, String items){
        RespMessage respMessage = new RespMessage();
        if (items == null || items.equals("[]") || ruleGroup == null){
            respMessage.setMsg("请选择网关");
            respMessage.setCode(-1);

            return respMessage;
        }

        RuleGroup group = JSONUtil.toBean(ruleGroup, RuleGroup.class);
        group.setGatewayAddressIds(items);


        if (group.getId() == null){
            ruleGroupService.insertSelective(group);
        }else {
            ruleGroupService.updateByPrimaryKeySelective(group);
        }

        return respMessage;
    }
}
