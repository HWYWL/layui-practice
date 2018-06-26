package com.yourui.web.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.Message;
import com.yourui.web.config.Config;
import com.yourui.web.model.Game;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.UserGroup;
import com.yourui.web.service.GameService;
import com.yourui.web.service.GatewayAddressService;
import com.yourui.web.service.RuleGroupService;
import com.yourui.web.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 用户组接口
 * @author YI
 * @date 2018-6-20 09:16:41
 */
@Controller
@RequestMapping("/userGroup")
public class UserGroupController {
    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    GatewayAddressService gatewayAddressService;
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

        List<UserGroup> userGroups = userGroupService.fidnAll();
        message.setData(userGroups);

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
            message.setMsg("id为空,查询数据失败!");
            message.setCode(-1);
            modelAndView.addObject("message", message);

            return modelAndView;
        }

        UserGroup userGroup = userGroupService.selectByPrimaryKey(id);
        String ruleGroupIds = userGroup.getRuleGroupIds();
        if(ruleGroupIds == null || ruleGroupIds.isEmpty()){
            message.setMsg("规则组不存在!");
            message.setCode(-1);
            modelAndView.addObject("message", message);

            return modelAndView;
        }

        StringBuffer buffer = new StringBuffer();

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(Long.valueOf(ruleGroupIds));
        buffer.append("所属规则组:" + ruleGroup.getRuleGroupName() + "\r\n\n");

        String gatewayAddressIds = userGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

        if (ids == null || ids.size() == 0){
            message.setMsg("此规则组没有关联网关!");
            message.setData("此规则组没有关联网关!");
            modelAndView.addObject("message", message);

            return modelAndView;
        }

        List<GatewayAddress> gatewayAddress = gatewayAddressService.selectByIds(ids);

        // 获取网关信息
        gatewayAddress.forEach(item-> buffer.append(item.getGatewayAddressName() + "\r\n"));

        // 获取所属游戏
        Game game = gameService.selectByPrimaryKey(userGroup.getGameId());
        buffer.append("所属游戏名称:" + game.getGameName());

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

        userGroupService.updateById(id);

        return message;
    }

    /**
     * 保存规则组信息
     * @param userGroup
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Message save(String userGroup, String items){
        Message message = new Message();

        if (userGroup == null || items == null){
            message.setMsg("请选择规则组");
            message.setCode(-1);

            return message;
        }

        UserGroup group = JSONUtil.toBean(userGroup, UserGroup.class);
        group.setGatewayAddressIds(items);

        userGroupService.insertSelective(group);

        return message;
    }

    /**
     * 按用户组执行
     * @param id
     * @return
     */
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public Message execute(Long id){
        Message message = new Message();

        UserGroup userGroup = userGroupService.selectByPrimaryKey(id);

        // 获取网关IP地址
        String gatewayAddressIds = userGroup.getGatewayAddressIds();
        if (gatewayAddressIds == null || gatewayAddressIds.length() == 0){
            message.setCode(-1);
            message.setData("网关IP不存在");

            return message;
        }

        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> longs = JSONUtil.toList(jsonArray, Long.class);
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(longs);

        List<String> arrayList = new ArrayList<>();

        gatewayAddresses.forEach(item -> arrayList.add(item.getIp()));

        // 获取一个随机网关IP用于游戏
        Random random = new Random();
        String ip = arrayList.get(random.nextInt(arrayList.size()));

        Long gameId = userGroup.getGameId();
        Game game = gameService.selectByPrimaryKey(gameId);

        Map<String, Object> map = new HashMap<>(16);
        map.put("gatewayIp", ip);
        map.put("gameIp", game.getIp());

        try {
            // 把网关ip和游戏ip发送到防御网关
            HttpUtil.post(Config.ADDRESS_PORT, map, 2000);
            message.setData("执行成功");
        }catch (Exception e){
            message.setCode(-1);
            message.setData(e.getCause().toString());
        }

        return message;
    }

}
