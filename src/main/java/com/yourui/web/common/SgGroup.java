package com.yourui.web.common;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * SG网关配置
 * @author YI
 * @date 2018-8-17 15:34:35
 */
public class SgGroup {
    /**
     * 游戏局数
     */
    private int ju;
    /**
     * 消耗房卡数
     */
    private int depCard;
    /**
     * 充值数
     */
    private int reCharge;
    /**
     * 配置文件列表
     */
    private List<ConfigFile> configs = Lists.newArrayList();

    public int getJu() {
        return ju;
    }

    public void setJu(int ju) {
        this.ju = ju;
    }

    public int getDepCard() {
        return depCard;
    }

    public void setDepCard(int depCard) {
        this.depCard = depCard;
    }

    public int getReCharge() {
        return reCharge;
    }

    public void setReCharge(int reCharge) {
        this.reCharge = reCharge;
    }

    public List<ConfigFile> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigFile> configs) {
        this.configs = configs;
    }

    /**
     * 配置文件
     */
    public static class ConfigFile {
        /**
         * 解密key
         */
        private String key;
        /**
         * 配置文件下载地址
         */
        private String url;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
