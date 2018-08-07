package com.yourui.web.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.Message;
import com.yourui.web.common.RespMessage;
import com.yourui.web.common.Status;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.Rules;
import com.yourui.web.model.UserGroup;
import com.yourui.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 公共方法接口
 * @author YI
 * @date 2018-7-10 11:06:21
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    RulesService rulesService;

    @Override
    public RespMessage findByRuleGroup(String ruleGroupName) {
        RespMessage respMessage = new RespMessage();

        if (StrUtil.isBlank(ruleGroupName)){
            respMessage.setMsg("规则名称为空!");
            respMessage.setCode(-1);

            return respMessage;
        }

        List<RuleGroup> ruleGroups = ruleGroupService.fidnByRuleGroupName(ruleGroupName);
        if (ruleGroups == null || ruleGroups.size() == 0){
            respMessage.setMsg("规则组不存在!");
            respMessage.setCode(-1);

            return respMessage;
        }

        List list = findByRuleMap(ruleGroups);

        respMessage.setData(list);

        return respMessage;
    }

    @Override
    public RespMessage findByRuleGroupIP(String ip) {
        RespMessage respMessage = new RespMessage();

        Map<String, Object> map = new HashMap<>(16);

        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIP(ip);
        if (gatewayAddresses == null || gatewayAddresses.size() == 0){
            return null;
        }

        GatewayAddress gatewayAddress = gatewayAddresses.get(0);
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();
        ruleGroups.forEach(item ->{
            String gatewayAddressIds = item.getGatewayAddressIds();
            JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
            List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

            //判断是否包含id
            if (ids.contains(gatewayAddress.getId())){
                String ruleGroupName = item.getRuleGroupName();
                map.put("ruleGroupName", ruleGroupName);

                List<Rules> rules = rulesService.selectByRuleGroupId(item.getId());
                List<Map> list = rulesSubdivide(rules);


                map.put("rules", list);
            }
        });

        respMessage.setData(map);

        return respMessage;
    }

    @Override
    public Message findByRuleGroupName(String ruleGroupName, Rules rules) {
        Message message = new Message();

        List<RuleGroup> ruleGroups = ruleGroupService.fidnByRuleGroupName(ruleGroupName);

        List list = findByRuleMap(ruleGroups, rules);
        message.setData(list);

        return message;
    }

    @Override
    public List findByRuleMap(List<RuleGroup> ruleGroups) {
        RuleGroup ruleGroup = ruleGroups.get(0);
        List<Rules> rules = rulesService.selectByRuleGroupId(ruleGroup.getId());
        List<Map> list = rulesSubdivide(rules);

        return list;
    }

    @Override
    public List<GatewayAddress> usableGatewayAddresses() {
        Set<Long> set = new HashSet<>();

        List<Long> longs = gatewayAddressService.fidnAllId(Status.GREEN.getValue());
        List<RuleGroup> ruleGroups = ruleGroupService.fidnAll();

        ruleGroups.forEach(item-> {
            String gatewayAddressIds = item.getGatewayAddressIds();
            JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
            List<Long> ids = JSONUtil.toList(jsonArray, Long.class);
            set.addAll(ids);
        });
        longs.removeAll(set);

        // 获取剩余可用网关
        return longs.size() == 0 ? new ArrayList<>() : gatewayAddressService.selectByIds(longs);
    }

    @Override
    public List<GatewayAddress> detailGatewayAddress(Long id) {
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ruleGroupIds = JSONUtil.toList(jsonArray, Long.class);

        if (ruleGroupIds == null || ruleGroupIds.size() == 0){
            // 此规则组没有关联规则组

            return null;
        }

        List<UserGroup> userGroups = userGroupService.selectByRuleGroupIds(String.valueOf(id));

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
            // 此组没有空余的安全网关
            return null;
        }

        //获取剩余可用网关
        return gatewayAddressService.selectByIds(ruleGroupIds);
    }

    @Override
    public List<GatewayAddress> accessGroupGatewayAddress(Long id) {
        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(id);
        String gatewayAddressIds = ruleGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ruleGroupIds = JSONUtil.toList(jsonArray, Long.class);

        if (ruleGroupIds == null || ruleGroupIds.size() == 0){
            // 此规则组没有关联规则组

            return null;
        }

        //获取剩余可用网关
        return gatewayAddressService.selectByIds(ruleGroupIds);
    }

    public List findByRuleMap(List<RuleGroup> ruleGroups, Rules rules) {
        RuleGroup ruleGroup = ruleGroups.get(0);
        List<Rules> rulesList = rulesService.selectByRuleGroupId(ruleGroup.getId());
        rulesList.remove(rules);
        List<Map> list = rulesSubdivide(rulesList);

        return list;
    }

    /**
     * 细分数据填充复用
     * @param rules
     * @return
     */
    private List<Map> rulesSubdivide(List<Rules> rules) {
        List<Map> list = new ArrayList<>();

        rules.forEach(items-> {
            Map<String, Object> mapTemp = new HashMap<>(16);
            mapTemp.put("fromPort", items.getFromPort());
            mapTemp.put("toPort", items.getToPort());
            mapTemp.put("toIp", items.getToIp());
            mapTemp.put("protocol", items.getAgreement());
            mapTemp.put("ruleId", items.getId());
            mapTemp.put("maxConcurrentConn", Long.valueOf(items.getMaxConcurrentConn()));
            mapTemp.put("maxConcurrentConnPerIp", Long.valueOf(items.getMaxConcurrentConnPerIp()));
            mapTemp.put("maxNewConnPerMinPerIp", Long.valueOf(items.getMaxNewConnPerMinPerIp()));
            mapTemp.put("revFirstPkgTimeoutMills", Long.valueOf(items.getRevFirstPkgTimeoutMills()));

            list.add(mapTemp);
        });

        return list;
    }
}
