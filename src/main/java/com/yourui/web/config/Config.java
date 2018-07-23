package com.yourui.web.config;

import com.yourui.web.utils.GitUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 配置
 * @author YI
 * @date 2018-6-25 11:00:45
 */
@Configuration
@MapperScan(basePackages = {"com.yourui.web.dao"})
@PropertySource("classpath:config/config.properties")
public class Config {

    /**
     * redis key
     */
    public static final String USER_REDIS_SESSION = "user_redis_session";

    /**
     * redis token超时时间（ms）
     */
    public static final int REDIS_TIMEOUT = 1000 * 60 * 30;

    /**
     * 请求地址
     */
    public static String ADDRESS_PORT;
    public static String ADDRESS_HTTP;
    public static String ADDRESS_HTTPS;

    /**
     * 远程库路径
     */
    public static String GIT_REMOTEPATH;

    /**
     * 文件地址前缀路径
     */
    public static String GIT_FILEPATH;
    /**
     * 下载已有仓库到本地路径
     */
    public static String GIT_LOCALPATH;
    /**
     * 远程服务器上的用户名
     */
    public static String GIT_USERNAME;
    /**
     * 远程服务器上的密码
     */
    public static String GIT_PASSWORD;

    /**
     * 初始化git仓库
     * @return
     * @throws GitAPIException
     */
    @Bean
    public Git initGit() throws GitAPIException, IOException {
        return GitUtil.gitClone(GIT_LOCALPATH, GIT_REMOTEPATH, GIT_USERNAME, GIT_PASSWORD);
    }

    @Value("${post.address.port}")
    public void setAddressPort(String addressPort) {
        ADDRESS_PORT = addressPort;
    }

    @Value("${post.address.http}")
    public void setAddressHttp(String addressHttp) {
        ADDRESS_HTTP = addressHttp;
    }

    @Value("${post.address.https}")
    public void setAddressHttps(String addressHttps) {
        ADDRESS_HTTPS = addressHttps;
    }

    @Value("${git.remote.path}")
    public void setGitRemotepath(String gitRemotepath) {
        GIT_REMOTEPATH = gitRemotepath;
    }

    @Value("${git.file.path}")
    public void setGitFilepath(String gitFilepath) {
        GIT_FILEPATH = gitFilepath;
    }

    @Value("${git.local.path}")
    public void setGitLocalpath(String gitLocalpath) {
        GIT_LOCALPATH = gitLocalpath;
    }

    @Value("${git.username}")
    public void setGitUsername(String gitUsername) {
        GIT_USERNAME = gitUsername;
    }

    @Value("${git.passworld}")
    public void setGitPassword(String gitPassword) {
        GIT_PASSWORD = gitPassword;
    }
}
