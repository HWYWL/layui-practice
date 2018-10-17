package com.yourui.web.service;

import com.yourui.web.model.RuleGroup;

import java.util.List;

/**
 * 规则组接口
 * @author YI
 * @date 2018-6-20 09:18:56
 */
public interface RuleGroupService {
    /**
     * 查询所有有效数据
     * @return
     */
    List<RuleGroup> fidnAll();

    /**
     * 根据规则组名 查找id
     * @param ruleGroupName 规则组名
     * @return
     */
    List<RuleGroup> fidnByRuleGroupName(String ruleGroupName);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    RuleGroup selectByPrimaryKey(Long id);

    /**
     * 更新数据
     * @param id
     * @return
     */
    int updateById(Long id);

    /**
     * 不为空则更新
     * @param ruleGroup
     * @return
     */
    int updateByPrimaryKeySelective(RuleGroup ruleGroup);

    /**
     * 保存数据
     * @param record
     * @return
     */
    int insertSelective(RuleGroup record);
}
