package com.yourui.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关管理app
 * @author YI
 * @date 2018-6-19 11:59:53
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.yourui.web.dao"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
