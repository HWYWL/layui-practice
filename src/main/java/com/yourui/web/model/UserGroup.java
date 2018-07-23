package com.yourui.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class UserGroup implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户组名
     */
    private String userGroupName;

    /**
     * 所属规则组的id
     */
    private String ruleGroupIds;

    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 加密秘钥key
     */
    private String encryptKey;

    /**
     * 所属该组的网关id
     */
    private String gatewayAddressIds;

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

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getRuleGroupIds() {
        return ruleGroupIds;
    }

    public void setRuleGroupIds(String ruleGroupIds) {
        this.ruleGroupIds = ruleGroupIds;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
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