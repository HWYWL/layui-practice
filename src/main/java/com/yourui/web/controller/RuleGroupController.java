package com.yourui.web.controller;

import cn.hutool.core.util.NetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.Message;
import com.yourui.web.common.Status;
import com.yourui.web.config.Config;
import com.yourui.web.model.*;
import com.yourui.web.service.*;
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
    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    RulesService rulesService;
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    GameService gameService;

    /**
     * 获取所有组信息有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public Message fidnAllGatewayAddress(){
        Message message = new Message();
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();
        RuleGroup[] scriptInfos = new RuleGroup[ruleGroups.size()];
        RuleGroup[] infos = ruleGroups.toArray(scriptInfos);

        message.setCount(ruleGroups.size());
        message.setData(infos);

        return message;
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
        Message message = new Message();

        if (id == null){
            message.setMsg("id为空,更新数据失败!");
            message.setCode(-1);
            modelAndView.addObject("message", message);

            return modelAndView;
        }

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

        if (ids == null || ids.size() == 0){
            message.setMsg("此规则组没有关联网关!");
            message.setData("此规则组没有关联网关!");
            modelAndView.addObject("message", message);

            return modelAndView;
        }

        List<GatewayAddress> gatewayAddress = gatewayAddressService.selectByIds(ids);
        StringBuffer buffer = new StringBuffer();
        gatewayAddress.forEach(item-> buffer.append(item.getGatewayAddressName() + "\r\n"));

        message.setData(buffer.toString());

        modelAndView.addObject("message", message);

        return modelAndView;
    }

    /**
     * 用户组查询可用网关
     * @param id
     * @return
     */
    @RequestMapping(value = "/detailGatewayAddress", method = RequestMethod.POST)
    @ResponseBody
    public Message detailGatewayAddress(Long id) {
        Message message = new Message();

        if (id == null){
            message.setMsg("id为空,无可用网关!");
            message.setCode(-1);

            return message;
        }

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ruleGroupIds = JSONUtil.toList(jsonArray, Long.class);

        if (ruleGroupIds == null || ruleGroupIds.size() == 0){
            message.setMsg("此规则组没有关联规则组!");
            message.setData("此规则组没有关联规则组!");

            return message;
        }

        List<UserGroup> userGroups = userGroupService.selectByRuleGroupIds(String.valueOf(id));
        List<GatewayAddress> gatewayAddress = new ArrayList<>();
        // 去重
        Set<Long> set = new HashSet<>();
        if (userGroups != null && userGroups.size() > 0){
            userGroups.forEach(item-> {
                String gaIds = item.getGatewayAddressIds();
                JSONArray array = JSONUtil.parseArray(gaIds);
                List<Long> ids = JSONUtil.toList(array, Long.class);
                set.addAll(ids);
            });
            ruleGroupIds.removeAll(set);
        }

        if (ruleGroupIds.size() == 0){
            message.setMsg("此组没有空余的安全网关!");
            message.setData(gatewayAddress);

            return message;
        }

        //获取剩余可用网关
        gatewayAddress = gatewayAddressService.selectByIds(ruleGroupIds);

        message.setData(gatewayAddress);

        return message;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delID", method = RequestMethod.POST)
    @ResponseBody
    public Message delID(Long id){
        Message message = new Message();
        if (id == null){
            message.setMsg("id为空,更新数据失败!");
            message.setCode(-1);

            return message;
        }

        ruleGroupService.updateById(id);

        return message;
    }

    /**
     * 执行规则组包含网关
     * @param id
     * @return
     */
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public Message execute(Long id) {
        Message message = new Message();

        // 获取此规则组的网关地址
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray gatewayAddressIdsArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> longs = JSONUtil.toList(gatewayAddressIdsArray, Long.class);
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(longs);

        // 获取规则组对应的规则
        List<Rules> rules = rulesService.selectByRuleGroupId(id);

        // 获取规则组中规则对应的游戏
        List<Long> gameIdList = new ArrayList<>();
        rules.forEach(item-> gameIdList.add(item.getGameId()));
        if (gameIdList == null || gameIdList.size() == 0){
            message.setData("暂无游戏关联");
            message.setCode(-1);
            return message;
        }

        List<Game> games = gameService.selectByIds(gameIdList);

        List<String> gameIpList = new ArrayList<>();
        List<String> gatewayIpList = new ArrayList<>();

        games.forEach(item-> gameIpList.add(item.getIp()));
        gatewayAddresses.forEach(item-> gatewayIpList.add(item.getIp()));

        Map<String, Object> map = new HashMap<>(16);
        map.put("gatewayIp", gatewayIpList);
        map.put("gameIp", gameIpList);

        try {
            // 把网关ip和游戏ip发送到防御网关
            HttpUtil.post(Config.ADDRESS_PORT, map, 2000);
        }catch (Exception e){
            message.setCode(-1);
            message.setData(e.getCause().toString());
        }

        return message;
    }

    /**
     * 保存规则组信息
     * @param ruleGroup
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Message save(String ruleGroup, String items){
        Message message = new Message();
        if (items == null && ruleGroup == null){
            message.setMsg("请选择网关");
            message.setCode(-1);

            return message;
        }

        RuleGroup group = JSONUtil.toBean(ruleGroup, RuleGroup.class);
        group.setGatewayAddressIds(items);

        ruleGroupService.insertSelective(group);

        return message;
    }
}
