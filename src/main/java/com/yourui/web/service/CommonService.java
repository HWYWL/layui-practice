package com.yourui.web.service;

import com.yourui.web.common.Message;
import com.yourui.web.common.RespMessage;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.Rules;

import java.util.List;

/**
 * 公共服务接口
 * @author YI
 * @date 2018-7-11 14:57:05
 */
public interface CommonService {
    /**
     * 根据规则名称获取服务器转发规则
     * @param ruleGroupName
     * @return
     */
    RespMessage findByRuleGroup(String ruleGroupName);

    /**
     * 通过ip获取网关组
     * @param ip 网关内网ip
     * @return
     */
    RespMessage findByRuleGroupIP(String ip);

    /**
     * 根据规则名称获取服务器转发规则,用户数据请求
     * @param ruleGroupName
     * @return
     */
    Message findByRuleGroupName(String ruleGroupName, Rules rules);

    /**
     * 获取返回客户端的信息
     * @param ruleGroups
     * @return
     */
    List findByRuleMap(List<RuleGroup> ruleGroups);

    /**
     * 查询可用剩余网关
     * @return
     */
    List<GatewayAddress> usableGatewayAddresses();

    /**
     * 查询可用剩余网关
     * @param id 规则组id
     * @return
     */
    List<GatewayAddress> detailGatewayAddress(Long id);

    /**
     * 获取该用户组所对应的所有网关（已删除的除外）
     * @param id 规则组id
     * @return
     */
    List<GatewayAddress> accessGroupGatewayAddress(Long id);
}
