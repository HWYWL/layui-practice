package com.yourui.web.service.impl;

import com.yourui.web.common.Status;
import com.yourui.web.dao.RulesMapper;
import com.yourui.web.model.Rules;
import com.yourui.web.model.RulesExample;
import com.yourui.web.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规则接口实现
 * @author YI
 * @date 2018-6-20 10:04:53
 */
@Service
public class RulesServiceImpl implements RulesService {
    @Autowired
    RulesMapper rulesMapper;

    @Override
    public List<Rules> selectByIds(List<Long> ids) {
        RulesExample example = new RulesExample();
        RulesExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        List<Rules> rules = rulesMapper.selectByExample(example);

        return rules;
    }

    @Override
    public List<Rules> selectByRuleGroupId(Long id) {
        RulesExample example = new RulesExample();
        RulesExample.Criteria criteria = example.createCriteria();

        criteria.andRuleGroupIdEqualTo(id);

        return rulesMapper.selectByExample(example);
    }

    @Override
    public List<Rules> fidnAll() {
        RulesExample example = new RulesExample();
        RulesExample.Criteria criteria = example.createCriteria();

        criteria.andDelEqualTo(Status.GREEN.getValue());

        return rulesMapper.selectByExample(example);
    }

    @Override
    public Rules selectByPrimaryKey(Long id) {
        return rulesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Long id) {
        Rules rules = new Rules();
        rules.setId(id);
        rules.setDel(Status.RED.getValue());

        return rulesMapper.updateByPrimaryKeySelective(rules);
    }

    @Override
    public int insertSelective(Rules record) {
        return rulesMapper.insertSelective(record);
    }
}
