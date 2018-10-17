package com.yourui.web.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.RespMessage;
import com.yourui.web.config.Config;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.Rules;
import com.yourui.web.model.UserGroup;
import com.yourui.web.service.*;
import com.yourui.web.utils.GitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关地址接口
 * @author YI
 * @date 2018-6-19 16:36:45
 */
@Controller
@RequestMapping("/gatewayAddress")
public class GatewayAddressController {
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
     * 获取所有网关有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage fidnAllGatewayAddress(){
        RespMessage respMessage = new RespMessage();
        List<GatewayAddress> gatewayAddressList = gatewayAddressService.fidnAll();
        GatewayAddress[] scriptInfos = new GatewayAddress[gatewayAddressList.size()];
        GatewayAddress[] infos = gatewayAddressList.toArray(scriptInfos);

        respMessage.setCount(gatewayAddressList.size());
        respMessage.setData(infos);

        return respMessage;
    }

    /**
     * 查看脚本
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detail(Long id) {
        ModelAndView modelAndView = new ModelAndView("script/code");
        List<GatewayAddress> list = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        RespMessage respMessage = new RespMessage();

        GatewayAddress gatewayAddress = gatewayAddressService.selectByPrimaryKey(id);
        list.add(gatewayAddress);
        commonService.setPortList(list);
        buffer.append("ID:" + gatewayAddress.getId() + "\r\n\n");
        buffer.append("内网IP:" + gatewayAddress.getInternalNetworkIp() + "\r\n\n");
        buffer.append("外网IP:" + gatewayAddress.getOutsideNetworkIp() + "\r\n\n");
        buffer.append("规则转发端口:" + gatewayAddress.getPortList() + "\r\n\n");
        buffer.append("网关名称:" + gatewayAddress.getGatewayAddressName() + "\r\n\n");
        buffer.append("描述:" + gatewayAddress.getRemark() + "\r\n\n");
        buffer.append("创建时间:" + DateUtil.formatDateTime(gatewayAddress.getCrttime()) + "\r\n\n");

        respMessage.setData(buffer.toString());
        modelAndView.addObject("message", respMessage);

        return modelAndView;
    }

    /**
     * 查看脚本 用于回显
     * @param id
     * @return
     */
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage echo(Long id) {
        RespMessage respMessage = new RespMessage();

        GatewayAddress gatewayAddress = gatewayAddressService.selectByPrimaryKey(id);
        respMessage.setData(gatewayAddress);

        return respMessage;
    }

    /**
     * 按用网关执行
     * @param id
     * @return
     */
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage execute(Long id){
        List<UserGroup> userGroupList = userGroupService.fidnAll();
        List<UserGroup> group = new ArrayList<>();
        RespMessage respMessage = new RespMessage();

        if (userGroupList == null || userGroupList.size() == 0){
            respMessage.setCode(-1);
            respMessage.setMsg("用户组暂无数据！");

            return respMessage;
        }

        userGroupList.forEach(items ->{
            JSONArray gatewayAddressIdsArray = JSONUtil.parseArray(items.getGatewayAddressIds());
            List<Long> longs = JSONUtil.toList(gatewayAddressIdsArray, Long.class);
            if (longs.indexOf(id) != -1){
                group.add(items);
            }
        });

        if (group == null || group.size() == 0){
            respMessage.setCode(-1);
            respMessage.setMsg("此网关暂未启用！");

            return respMessage;
        }

        group.forEach(items -> commonService.gitDataCombination(items, respMessage));

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


        gatewayAddressService.updateById(id);

        return respMessage;
    }

    /**
     * 保存网关信息
     * @param gatewayAddress
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespMessage save(@RequestBody GatewayAddress gatewayAddress){
        RespMessage respMessage = new RespMessage();
        if (gatewayAddress == null){
            respMessage.setMsg("id为空,更新数据失败!");
            respMessage.setCode(-1);

            return respMessage;
        }

        if (gatewayAddress.getId() == null){
            gatewayAddressService.insertSelective(gatewayAddress);
        }else {
            gatewayAddressService.updateByPrimaryKeySelective(gatewayAddress);
        }


        return respMessage;
    }

    /**
     * 同步from端口配置数据
     * @return
     */
    @RequestMapping(value = "/synConfig")
    @ResponseBody
    public RespMessage synConfig(){
        RespMessage respMessage = new RespMessage();
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();

        try {
            ruleGroups.forEach(ruleGroup ->{
                String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
                JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
                List<Long> ids = JSONUtil.toList(jsonArray, Long.class);
                List<GatewayAddress> gatewayAddressesList = gatewayAddressService.selectByIds(ids);

                List<Rules> rulesList = rulesService.selectByRuleGroupId(ruleGroup.getId());
                gatewayAddressesList.forEach(gatewayAddresse ->{
                    String gatewayAddressePortList = gatewayAddresse.getPortList();

                    List<Long> ports = new ArrayList<>();
                    if (gatewayAddressePortList != null && gatewayAddressePortList.length() != 0){
                        JSONArray json = JSONUtil.parseArray(gatewayAddressePortList);
                        ports = JSONUtil.toList(json, Long.class);
                    }

                    // 数目不同说明存在修改 重新生成对应的数据
                    if (ports.size() != rulesList.size()) {
                        List<Long> portList = new ArrayList<>();

                        // 生成随机端口
                        while (portList.size() < rulesList.size()) {
                            Long anInt = RandomUtil.randomLong(Config.MIN_PORT, Config.MAX_PORT);
                            // 判断是否包含
                            if (!portList.contains(anInt)) {
                                portList.add(anInt);
                            }
                        }

                        String str = JSONUtil.toJsonStr(portList);
                        gatewayAddresse.setPortList(str);

                        gatewayAddressService.updateByPrimaryKeySelective(gatewayAddresse);
                    }
                });

            });
        }catch (Exception e){
            respMessage.setCode(-1);
            respMessage.setMsg("数据同步错误,ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return respMessage;
    }
}
