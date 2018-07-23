package com.yourui.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class GatewayAddress implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 网关内网ip地址
     */
    private String internalNetworkIp;

    /**
     * 网关外网ip地址
     */
    private String outsideNetworkIp;

    /**
     * 网关服务器名称
     */
    private String gatewayAddressName;

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

    public String getInternalNetworkIp() {
        return internalNetworkIp;
    }

    public void setInternalNetworkIp(String internalNetworkIp) {
        this.internalNetworkIp = internalNetworkIp;
    }

    public String getOutsideNetworkIp() {
        return outsideNetworkIp;
    }

    public void setOutsideNetworkIp(String outsideNetworkIp) {
        this.outsideNetworkIp = outsideNetworkIp;
    }

    public String getGatewayAddressName() {
        return gatewayAddressName;
    }

    public void setGatewayAddressName(String gatewayAddressName) {
        this.gatewayAddressName = gatewayAddressName;
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
        return "GatewayAddress{" +
                "id=" + id +
                ", internalNetworkIp='" + internalNetworkIp + '\'' +
                ", outsideNetworkIp='" + outsideNetworkIp + '\'' +
                ", gatewayAddressName='" + gatewayAddressName + '\'' +
                ", remark='" + remark + '\'' +
                ", del=" + del +
                ", crttime=" + crttime +
                '}';
    }
}