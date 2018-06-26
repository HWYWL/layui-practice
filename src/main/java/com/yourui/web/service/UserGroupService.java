package com.yourui.web.service;

import com.yourui.web.model.UserGroup;

import java.util.List;

/**
 * 规则组接口
 * @author YI
 * @date 2018-6-21 15:14:48
 */
public interface UserGroupService {
    /**
     * 查询所有有效数据
     * @return
     */
    List<UserGroup> fidnAll();

    /**
     * 查找规则组
     * @param id
     * @return
     */
    List<UserGroup> selectByRuleGroupIds(String id);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    UserGroup selectByPrimaryKey(Long id);

    /**
     * 更新数据
     * @param id
     * @return
     */
    int updateById(Long id);

    /**
     * 保存数据
     * @param record
     * @return
     */
    int insertSelective(UserGroup record);
}
