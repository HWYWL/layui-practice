package com.yourui.web;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;

/**
 * 密码生成
 */
public class PasswordTest {

    @Test
    public void test1(){
        String password = "zVOt5ZkO";
        String hex = DigestUtil.md5Hex(password);
        System.out.println(hex);
    }
}
