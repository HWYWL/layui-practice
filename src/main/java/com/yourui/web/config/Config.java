package com.yourui.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置
 * @author YI
 * @date 2018-6-25 11:00:45
 */
@Configuration
@PropertySource("classpath:config/config.properties")
public class Config {

    /**
     * 请求地址
     */
    public static String ADDRESS_PORT;

    @Value("${post.address.port}")
    public void setAddressPort(String addressPort) {
        ADDRESS_PORT = addressPort;
    }
}
