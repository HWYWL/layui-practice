package com.yourui.web.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import com.yourui.web.config.Config;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * git操作
 * @author YI
 * @date 2018-7-12 17:51:39
 */
public class GitUtil {
    private static Logger logger = LoggerFactory.getLogger(GitUtil.class);

    /**
     * 克隆远程库
     * @param localPath 下载已有仓库到本地路径
     * @param remotePath    远程库路径
     * @param username  远程服务器上的用户名
     * @param password  远程服务器上的密码
     */
    public static Git gitClone(String localPath, String remotePath, String username, String password) throws GitAPIException, IOException {
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(username, password);

        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

        // 目录不为空则删除整个目录
        File file = new File(localPath);
        Git git;

        // 文件夹存在pull
        if (file.exists()){
            git = new Git(new FileRepository(localPath+"/.git"));
            git.pull().setRemoteBranchName("master").setCredentialsProvider(provider).call();
        }else {
            //设置远程URI、分支、下载存放路径、权限验证
            git = cloneCommand.setURI(remotePath)
                    .setBranch("master")
                    .setDirectory(new File(localPath))
                    .setCredentialsProvider(provider)
                    .call();
            Git.init().setDirectory(file).call();
        }

        return git;
    }

    /**
     * 创建文件并提交到git上
     * @param store 仓库名称
     * @param userGroupName 用户组名称
     * @param data  写入数据
     * @throws IOException
     * @throws GitAPIException
     */
    public static void commitFiles(String store, String userGroupName, String data) throws IOException, GitAPIException{
        UsernamePasswordCredentialsProvider provider = gitProvider(store);
        // .git文件夹地址
        String gitLocalPath = gitLocalPath(store);

        File file = FileUtil.file(gitLocalPath + CharUtil.SLASH + DigestUtil.md5Hex(userGroupName), DigestUtil.md5Hex(userGroupName));
        //git仓库地址
        Git git = new Git(new FileRepository(gitLocalPath + "/.git"));

        // 文件夹存在pull
        if (file.exists()){
            git.pull().setRemoteBranchName("master").setCredentialsProvider(provider).call();
        }
        // 覆盖写入
        FileUtil.writeUtf8String(data, file);

        // 添加目录，可以把目录下的文件都添加到暂存区
        git.add().addFilepattern(DigestUtil.md5Hex(userGroupName)).call();
        //提交
        git.commit().setMessage("新增配置").call();
        //推送到远程
        Iterable<PushResult> origin = git.push().setRemote("origin").setCredentialsProvider(provider).call();
        logger.info("推送结果：" + origin.iterator().next().getRemoteUpdates());
        logger.info("推送结果：" + origin.iterator().next().getAdvertisedRefs());
        System.out.println(origin);
    }


    /**
     * 获取git文件
     * @return
     */
    public static String gitLocalPath(String store){
        String localPath;

        if (store.equals(Config.GITHUB_PROD_LOCALPATH)){
            if ("prod".equals(Config.SPRING_PROFILES_ACTIVE)){
                localPath = Config.GITHUB_PROD_LOCALPATH;
            }else {
                localPath = Config.GITHUB_DEV_LOCALPATH;
            }
        } else {
            if ("prod".equals(Config.SPRING_PROFILES_ACTIVE)){
                localPath = Config.GITEE_PROD_LOCALPATH;
            }else {
                localPath = Config.GITEE_DEV_LOCALPATH;
            }
        }

        return localPath;
    }

    /**
     * 获取git本地地址文件
     * @return
     */
    public static String gitFilePath(String store){
        String filePath;

        if (store.equals(Config.GITHUB_PROD_LOCALPATH)){
            if ("prod".equals(Config.SPRING_PROFILES_ACTIVE)){
                filePath = Config.GITHUB_PROD_FILEPATH;
            }else {
                filePath = Config.GITHUB_DEV_FILEPATH;
            }
        }else {
            if ("prod".equals(Config.SPRING_PROFILES_ACTIVE)){
                filePath = Config.GITEE_PROD_FILEPATH;
            }else {
                filePath = Config.GITEE_DEV_FILEPATH;
            }
        }

        return filePath;
    }

    /**
     * 获取git的用户对象
     * @return
     */
    public static UsernamePasswordCredentialsProvider gitProvider(String store){
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider;

        if (store.equals(Config.GITEE_PROD_LOCALPATH)){
            if ("prod".equals(Config.SPRING_PROFILES_ACTIVE)){
                usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(Config.GITEE_PROD_USERNAME, Config.GITEE_PROD_PASSWORD);
            }else {
                usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(Config.GITEE_DEV_USERNAME, Config.GITEE_DEV_PASSWORD);
            }
        }else {
            if ("prod".equals(Config.SPRING_PROFILES_ACTIVE)){
                usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(Config.GITHUB_PROD_USERNAME, Config.GITHUB_PROD_PASSWORD);
            }else {
                usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(Config.GITHUB_DEV_USERNAME, Config.GITHUB_DEV_PASSWORD);
            }
        }

        return usernamePasswordCredentialsProvider;
    }
}
