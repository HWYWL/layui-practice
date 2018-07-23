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
     * 规则组ID
     */
    private Long ruleGroupId;

    /**
     * 来源端口
     */
    private Long fromPort;

    /**
     * 转发端口
     */
    private Long toPort;

    /**
     * 转发地址
     */
    private String toIp;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 协议
     */
    private String agreement;

    /**
     * 代理最大并发连接数
     */
    private String maxConcurrentConn;

    /**
     * 每个ip最大并发连接数
     */
    private String maxConcurrentConnPerIp;

    /**
     * 每个ip每分钟最大新建连接数
     */
    private String maxNewConnPerMinPerIp;

    /**
     * 首包超时毫秒数
     */
    private String revFirstPkgTimeoutMills;

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

    public Long getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(Long ruleGroupId) {
        this.ruleGroupId = ruleGroupId;
    }

    public Long getFromPort() {
        return fromPort;
    }

    public void setFromPort(Long fromPort) {
        this.fromPort = fromPort;
    }

    public Long getToPort() {
        return toPort;
    }

    public void setToPort(Long toPort) {
        this.toPort = toPort;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getMaxConcurrentConn() {
        return maxConcurrentConn;
    }

    public void setMaxConcurrentConn(String maxConcurrentConn) {
        this.maxConcurrentConn = maxConcurrentConn;
    }

    public String getMaxConcurrentConnPerIp() {
        return maxConcurrentConnPerIp;
    }

    public void setMaxConcurrentConnPerIp(String maxConcurrentConnPerIp) {
        this.maxConcurrentConnPerIp = maxConcurrentConnPerIp;
    }

    public String getMaxNewConnPerMinPerIp() {
        return maxNewConnPerMinPerIp;
    }

    public void setMaxNewConnPerMinPerIp(String maxNewConnPerMinPerIp) {
        this.maxNewConnPerMinPerIp = maxNewConnPerMinPerIp;
    }

    public String getRevFirstPkgTimeoutMills() {
        return revFirstPkgTimeoutMills;
    }

    public void setRevFirstPkgTimeoutMills(String revFirstPkgTimeoutMills) {
        this.revFirstPkgTimeoutMills = revFirstPkgTimeoutMills;
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
                ", ruleGroupId=" + ruleGroupId +
                ", fromPort=" + fromPort +
                ", toPort=" + toPort +
                ", toIp='" + toIp + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", agreement='" + agreement + '\'' +
                ", maxConcurrentConn='" + maxConcurrentConn + '\'' +
                ", maxConcurrentConnPerIp='" + maxConcurrentConnPerIp + '\'' +
                ", maxNewConnPerMinPerIp='" + maxNewConnPerMinPerIp + '\'' +
                ", revFirstPkgTimeoutMills='" + revFirstPkgTimeoutMills + '\'' +
                ", remark='" + remark + '\'' +
                ", del=" + del +
                ", crttime=" + crttime +
                '}';
    }
}