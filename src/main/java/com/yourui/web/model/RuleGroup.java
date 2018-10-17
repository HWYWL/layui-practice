package com.yourui.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class RuleGroup implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 规则组名
     */
    private String ruleGroupName;

    /**
     * 所属该组的网关id
     */
    private String gatewayAddressIds;

    /**
     * 备注
     */
    private String remark;

    /**
     * 随机端口 0：启用、-1：关闭
     */
    private Byte enable;

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

    public String getRuleGroupName() {
        return ruleGroupName;
    }

    public void setRuleGroupName(String ruleGroupName) {
        this.ruleGroupName = ruleGroupName;
    }

    public String getGatewayAddressIds() {
        return gatewayAddressIds;
    }

    public void setGatewayAddressIds(String gatewayAddressIds) {
        this.gatewayAddressIds = gatewayAddressIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
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
}