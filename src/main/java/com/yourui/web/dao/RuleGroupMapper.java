package com.yourui.web.dao;

import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.RuleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RuleGroupMapper {
    long countByExample(RuleGroupExample example);

    int deleteByExample(RuleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RuleGroup record);

    int insertSelective(RuleGroup record);

    List<RuleGroup> selectByExample(RuleGroupExample example);

    RuleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    int updateByExample(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    int updateByPrimaryKeySelective(RuleGroup record);

    int updateByPrimaryKey(RuleGroup record);
}