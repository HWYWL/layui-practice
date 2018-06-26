package com.yourui.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Rules implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 游戏表ID
     */
    private Long gameId;

    /**
     * 规则组ID
     */
    private Long ruleGroupId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0：正常使用、-1：已被删除
     */
    private Byte del;

    /**
     * 创建时间
     */
    private Date crttime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(Long ruleGroupId) {
        this.ruleGroupId = ruleGroupId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", ruleGroupId=" + ruleGroupId +
                ", ruleName='" + ruleName + '\'' +
                ", remark='" + remark + '\'' +
                ", del=" + del +
                ", crttime=" + crttime +
                '}';
    }
}