package com.yourui.web.service.impl;

import com.yourui.web.common.Status;
import com.yourui.web.dao.UserGroupMapper;
import com.yourui.web.model.UserGroup;
import com.yourui.web.model.UserGroupExample;
import com.yourui.web.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规则组接口业务实现
 * @author YI
 * @date 2018-6-20 09:20:54
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {
    @Autowired
    UserGroupMapper userGroupMapper;


    @Override
    public List<UserGroup> fidnAll() {
        UserGroupExample example = new UserGroupExample();
        UserGroupExample.Criteria criteria = example.createCriteria();

        criteria.andDelNotEqualTo(Status.RED.getValue());

        return userGroupMapper.selectByExample(example);
    }

    @Override
    public List<UserGroup> selectByRuleGroupIds(String id) {
        UserGroupExample example = new UserGroupExample();
        UserGroupExample.Criteria criteria = example.createCriteria();

        criteria.andDelNotEqualTo(Status.RED.getValue());
        criteria.andRuleGroupIdsEqualTo(id);

        return userGroupMapper.selectByExample(example);
    }

    @Override
    public UserGroup selectByPrimaryKey(Long id) {

        return userGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Long id) {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(id);
        userGroup.setDel(Status.RED.getValue());

        return userGroupMapper.updateByPrimaryKeySelective(userGroup);
    }

    @Override
    public int updateByPrimaryKeySelective(UserGroup record) {
        return userGroupMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(UserGroup record) {
        return userGroupMapper.insertSelective(record);
    }
}
