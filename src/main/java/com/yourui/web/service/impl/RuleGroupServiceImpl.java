package com.yourui.web.service.impl;

import com.yourui.web.common.Status;
import com.yourui.web.dao.RuleGroupMapper;
import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.RuleGroupExample;
import com.yourui.web.service.RuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规则组接口业务实现
 * @author YI
 * @date 2018-6-20 09:20:54
 */
@Service
public class RuleGroupServiceImpl implements RuleGroupService {
    @Autowired
    RuleGroupMapper ruleGroupMapper;

    @Override
    public List<RuleGroup> fidnAll() {
        RuleGroupExample example = new RuleGroupExample();
        RuleGroupExample.Criteria criteria = example.createCriteria();

        criteria.andDelEqualTo(Status.GREEN.getValue());

        return ruleGroupMapper.selectByExample(example);
    }

    @Override
    public List<RuleGroup> fidnByRuleGroupName(String ruleGroupName) {
        RuleGroupExample example = new RuleGroupExample();
        RuleGroupExample.Criteria criteria = example.createCriteria();

        criteria.andRuleGroupNameEqualTo(ruleGroupName);
        criteria.andDelNotEqualTo(Status.RED.getValue());

        return ruleGroupMapper.selectByExample(example);
    }

    @Override
    public RuleGroup selectByPrimaryKey(Long id) {
        return ruleGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Long id) {
        RuleGroup ruleGroup = new RuleGroup();
        ruleGroup.setId(id);
        ruleGroup.setDel(Status.RED.getValue());

        return ruleGroupMapper.updateByPrimaryKeySelective(ruleGroup);
    }

    @Override
    public int updateByPrimaryKeySelective(RuleGroup ruleGroup) {

        return ruleGroupMapper.updateByPrimaryKeySelective(ruleGroup);
    }

    @Override
    public int insertSelective(RuleGroup record) {
        return ruleGroupMapper.insertSelective(record);
    }
}
