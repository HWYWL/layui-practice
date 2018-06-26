package com.yourui.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.Message;
import com.yourui.web.common.RulesPlus;
import com.yourui.web.common.Status;
import com.yourui.web.model.*;
import com.yourui.web.service.*;
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
    @Autowired
    RulesService rulesService;
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    GameService gameService;
    @Autowired
    UserGroupService userGroupService;

    /**
     * 获取所有游戏有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public Message fidnAllGatewayAddress(){
        Message message = new Message();
        ArrayList<RulesPlus> list = new ArrayList<>();
        List<Rules> rulesList = rulesService.fidnAll();

        for (Rules rules: rulesList) {
            RulesPlus item = new RulesPlus();
            BeanUtil.copyProperties(rules, item);
            Long gameId = item.getGameId();
            Long ruleGroupId = item.getRuleGroupId();
            Game game = gameService.selectByPrimaryKey(gameId);
            RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(ruleGroupId);
            item.setGameName(game.getGameName());
            item.setRuleGroupName(ruleGroup.getRuleGroupName());
            list.add(item);
        }

        message.setData(list);

        return message;
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
        Message message = new Message();

        Rules rules = rulesService.selectByPrimaryKey(id);
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(rules.getRuleGroupId());

        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);
        List<GatewayAddress> gatewayAddressesList = gatewayAddressService.selectByIds(ids);

        StringBuffer buffer = new StringBuffer();
        gatewayAddressesList.forEach(item-> buffer.append(item.getGatewayAddressName() + "\r\n"));

        message.setData(buffer.toString());

        modelAndView.addObject("message", message);

        return modelAndView;
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

        rulesService.updateById(id);

        return message;
    }

    /**
     * 保存游戏信息
     * @param rules
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Message save(@RequestBody Rules rules){
        Message message = new Message();
        if (rules == null){
            message.setMsg("请先填写数据,更新数据失败!");
            message.setCode(-1);

            return message;
        }

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(rules.getRuleGroupId());
        rules.setRuleGroupId(ruleGroup.getId());

        rulesService.insertSelective(rules);

        return message;
    }

    /**
     * 公共数据
     * @return
     */
    @RequestMapping("/fidnNetGroupGateway")
    @ResponseBody
    public Message fidnNetGroupGateway(){
        Message message = new Message();
        HashMap<String, Object> map = new HashMap<>();

        List<GatewayAddress> gatewayAddresses = gatewayAddressService.fidnAll();
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();
        List<UserGroup> userGroups = userGroupService.fidnAll();
        List<Game> games = gameService.fidnAll();

        Set<Long> set = new HashSet<>();
        List<Long> longs = gatewayAddressService.fidnAllId(Status.GREEN.getValue());
        ruleGroups.forEach(item-> {
            String gatewayAddressIds = item.getGatewayAddressIds();
            JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
            List<Long> ids = JSONUtil.toList(jsonArray, Long.class);
            set.addAll(ids);
        });
        longs.removeAll(set);

        // 获取剩余可用网关
        List<GatewayAddress> usableGatewayAddresses = gatewayAddressService.selectByIds(longs);

        map.put("gatewayAddresses", gatewayAddresses);
        map.put("ruleGroups", ruleGroups);
        map.put("userGroups", userGroups);
        map.put("games", games);
        map.put("usableGatewayAddresses", usableGatewayAddresses);

        message.setData(map);

        return message;
    }
}
