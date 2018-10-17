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

/**
 * 配置
 * @author YI
 * @date 2018-6-25 11:00:45
 */
@Configuration
@MapperScan(basePackages = {"com.yourui.web.dao"})
@PropertySource({
        "classpath:config/config-dev.properties",
        "classpath:config/config-prod.properties",
        "classpath:application.properties"
})
public class Config {

    /**
     * 环境（开发或者生产）
     */
    public static String SPRING_PROFILES_ACTIVE;

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
     * 随机最小端口号
     */
    public static Long MIN_PORT;
    /**
     * 随机最大端口号
     */
    public static Long MAX_PORT;

    /**
     * 远程库路径 开发
     */
    public static String GITEE_DEV_REMOTEPATH;

    /**
     * 文件地址前缀路径 开发
     */
    public static String GITEE_DEV_FILEPATH;
    /**
     * 下载已有仓库到本地路径 开发
     */
    public static String GITEE_DEV_LOCALPATH;
    /**
     * 远程服务器上的用户名 开发
     */
    public static String GITEE_DEV_USERNAME;
    /**
     * 远程服务器上的密码 开发
     */
    public static String GITEE_DEV_PASSWORD;

    /**
     * 远程库路径 开发
     */
    public static String GITHUB_DEV_REMOTEPATH;
    /**
     * 文件地址前缀路径 开发
     */
    public static String GITHUB_DEV_FILEPATH;
    /**
     * 下载已有仓库到本地路径 开发
     */
    public static String GITHUB_DEV_LOCALPATH;
    /**
     * 远程服务器上的用户名 开发
     */
    public static String GITHUB_DEV_USERNAME;
    /**
     * 远程服务器上的密码 开发
     */
    public static String GITHUB_DEV_PASSWORD;

    /**
     * 远程库路径 正式
     */
    public static String GITEE_PROD_REMOTEPATH;

    /**
     * 文件地址前缀路径 正式
     */
    public static String GITEE_PROD_FILEPATH;
    /**
     * 下载已有仓库到本地路径 正式
     */
    public static String GITEE_PROD_LOCALPATH;
    /**
     * 远程服务器上的用户名 正式
     */
    public static String GITEE_PROD_USERNAME;
    /**
     * 远程服务器上的密码 正式
     */
    public static String GITEE_PROD_PASSWORD;

    /**
     * 远程库路径 正式
     */
    public static String GITHUB_PROD_REMOTEPATH;

    /**
     * 文件地址前缀路径 正式
     */
    public static String GITHUB_PROD_FILEPATH;
    /**
     * 下载已有仓库到本地路径 正式
     */
    public static String GITHUB_PROD_LOCALPATH;
    /**
     * 远程服务器上的用户名 正式
     */
    public static String GITHUB_PROD_USERNAME;
    /**
     * 远程服务器上的密码 正式
     */
    public static String GITHUB_PROD_PASSWORD;

    /**
     * 初始化gitee仓库
     * @return
     * @throws GitAPIException
     */
    @Bean
    public Git initGitee() throws GitAPIException, IOException {
        Git git;
        if (SPRING_PROFILES_ACTIVE != null && "prod".equals(SPRING_PROFILES_ACTIVE)){
            git = GitUtil.gitClone(GITEE_PROD_LOCALPATH, GITEE_PROD_REMOTEPATH, GITEE_PROD_USERNAME, GITEE_PROD_PASSWORD);
        }else {
            git = GitUtil.gitClone(GITEE_DEV_LOCALPATH, GITEE_DEV_REMOTEPATH, GITEE_DEV_USERNAME, GITEE_DEV_PASSWORD);
        }

        return git;
    }

    /**
     * 初始化GitHub仓库
     * @return
     * @throws GitAPIException
     */
    @Bean
    public Git initGithub() throws GitAPIException, IOException {
        Git git;
        if (SPRING_PROFILES_ACTIVE != null && "prod".equals(SPRING_PROFILES_ACTIVE)){
            git = GitUtil.gitClone(GITHUB_PROD_LOCALPATH, GITHUB_PROD_REMOTEPATH, GITHUB_PROD_USERNAME, GITHUB_PROD_PASSWORD);
        }else {
            git = GitUtil.gitClone(GITHUB_DEV_LOCALPATH, GITHUB_DEV_REMOTEPATH, GITHUB_DEV_USERNAME, GITHUB_DEV_PASSWORD);
        }

        return git;
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

    @Value("${spring.profiles.active}")
    public void setSpringProfilesActive(String springProfilesActive) {
        SPRING_PROFILES_ACTIVE = springProfilesActive;
    }

    @Value("${gitee.dev.remote.path}")
    public void setGiteeDevRemotepath(String giteeDevRemotepath) {
        GITEE_DEV_REMOTEPATH = giteeDevRemotepath;
    }

    @Value("${gitee.dev.file.path}")
    public void setGiteeDevFilepath(String giteeDevFilepath) {
        GITEE_DEV_FILEPATH = giteeDevFilepath;
    }

    @Value("${gitee.dev.local.path}")
    public void setGiteeDevLocalpath(String giteeDevLocalpath) {
        GITEE_DEV_LOCALPATH = giteeDevLocalpath;
    }

    @Value("${gitee.dev.username}")
    public void setGiteeDevUsername(String giteeDevUsername) {
        GITEE_DEV_USERNAME = giteeDevUsername;
    }

    @Value("${gitee.dev.passworld}")
    public void setGiteeDevPassword(String giteeDevPassword) {
        GITEE_DEV_PASSWORD = giteeDevPassword;
    }

    @Value("${gitee.prod.remote.path}")
    public void setGiteeProdRemotepath(String giteeProdRemotepath) {
        GITEE_PROD_REMOTEPATH = giteeProdRemotepath;
    }

    @Value("${gitee.prod.file.path}")
    public void setGiteeProdFilepath(String giteeProdFilepath) {
        GITEE_PROD_FILEPATH = giteeProdFilepath;
    }

    @Value("${gitee.prod.local.path}")
    public void setGiteeProdLocalpath(String giteeProdLocalpath) {
        GITEE_PROD_LOCALPATH = giteeProdLocalpath;
    }

    @Value("${gitee.prod.username}")
    public void setGiteeProdUsername(String giteeProdUsername) {
        GITEE_PROD_USERNAME = giteeProdUsername;
    }

    @Value("${gitee.prod.passworld}")
    public void setGiteeProdPassword(String giteeProdPassword) {
        GITEE_PROD_PASSWORD = giteeProdPassword;
    }

    @Value("${github.dev.remote.path}")
    public void setGithubDevRemotepath(String githubDevRemotepath) {
        GITHUB_DEV_REMOTEPATH = githubDevRemotepath;
    }

    @Value("${github.dev.file.path}")
    public void setGithubDevFilepath(String githubDevFilepath) {
        GITHUB_DEV_FILEPATH = githubDevFilepath;
    }

    @Value("${github.dev.local.path}")
    public void setGithubDevLocalpath(String githubDevLocalpath) {
        GITHUB_DEV_LOCALPATH = githubDevLocalpath;
    }

    @Value("${github.dev.username}")
    public void setGithubDevUsername(String githubDevUsername) {
        GITHUB_DEV_USERNAME = githubDevUsername;
    }

    @Value("${github.dev.passworld}")
    public void setGithubDevPassword(String githubDevPassword) {
        GITHUB_DEV_PASSWORD = githubDevPassword;
    }

    @Value("${github.prod.remote.path}")
    public void setGithubProdRemotepath(String githubProdRemotepath) {
        GITHUB_PROD_REMOTEPATH = githubProdRemotepath;
    }

    @Value("${github.prod.file.path}")
    public void setGithubProdFilepath(String githubProdFilepath) {
        GITHUB_PROD_FILEPATH = githubProdFilepath;
    }

    @Value("${github.prod.local.path}")
    public void setGithubProdLocalpath(String githubProdLocalpath) {
        GITHUB_PROD_LOCALPATH = githubProdLocalpath;
    }

    @Value("${github.prod.username}")
    public void setGithubProdUsername(String githubProdUsername) {
        GITHUB_PROD_USERNAME = githubProdUsername;
    }

    @Value("${github.prod.passworld}")
    public void setGithubProdPassword(String githubProdPassword) {
        GITHUB_PROD_PASSWORD = githubProdPassword;
    }

    @Value("${sg.min.port}")
    public void setMinPort(Long minPort) {
        MIN_PORT = minPort;
    }
    @Value("${sg.max.port}")
    public void setMaxPort(Long maxPort) {
        MAX_PORT = maxPort;
    }
}
