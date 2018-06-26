package com.yourui.web.service;

import com.yourui.web.common.RulesPlus;
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
     * 保存数据
     * @param record
     * @return
     */
    int insertSelective(Rules record);
}
