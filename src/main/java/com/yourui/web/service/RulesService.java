package com.yourui.web.service;

import com.yourui.web.model.Rules;

import java.util.List;

/**
 * 规则接口
 * @author YI
 * @date 2018-6-20 09:59:34
 */
public interface RulesService {
    /**
     * 根据id 查找
     * @param ids
     * @return
     */
    List<Rules> selectByIds(List<Long> ids);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    List<Rules> selectByRuleGroupId(Long id);

    /**
     * 查询所有有效数据
     * @return
     */
    List<Rules> fidnAll();

    /**
     * 查找端口是否已经使用
     * @return
     */
    List<Rules> fidnPort(Long port);

    /**
     * 用于更新 不同id相同端口数据，不允许更新
     * @param rules
     * @return
     */
    List<Rules> fidnDifferentPort(Rules rules);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    Rules selectByPrimaryKey(Long id);

    /**
     * 更新数据
     * @param id
     * @return
     */
    int updateById(Long id);

    /**
     * 不为空则更新
     * @param rules
     * @return
     */
    int updateByPrimaryKeySelective(Rules rules);

    /**
     * 保存数据
     * @param record
     * @return
     */
    int insertSelective(Rules record);
}
