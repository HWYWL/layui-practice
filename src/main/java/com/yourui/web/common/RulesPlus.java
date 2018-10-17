package com.yourui.web.common;

import com.yourui.web.model.Rules;

import java.io.Serializable;

/**
 * @author YI
 */
public class RulesPlus extends Rules implements Serializable {
    /**
     * 用户组名
     */
    private String userGroupName;
    /**
     * 规则组名
     */
    private String ruleGroupName;

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getRuleGroupName() {
        return ruleGroupName;
    }

    public void setRuleGroupName(String ruleGroupName) {
        this.ruleGroupName = ruleGroupName;
    }

    @Override
    public String toString() {
        return "RulesPlus{" +
                "userGroupName='" + userGroupName + '\'' +
                ", ruleGroupName='" + ruleGroupName + '\'' +
                '}';
    }
}