package com.yourui.web.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.yourui.web.config.Config;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

/**
 * git操作
 * @author YI
 * @date 2018-7-12 17:51:39
 */
public class GitUtil {

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
     * @throws IOException
     * @throws GitAPIException
     */
    public static void commitFiles(String userGroupName, String data) throws IOException, GitAPIException{
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider(Config.GIT_USERNAME, Config.GIT_PASSWORD);
        File file = FileUtil.file(Config.GIT_LOCALPATH + CharUtil.SLASH + DigestUtil.md5Hex(userGroupName), DigestUtil.md5Hex(userGroupName));
        // 覆盖写入
        FileUtil.writeUtf8String(data, file);
        //git仓库地址
        Git git = new Git(new FileRepository(Config.GIT_LOCALPATH + "/.git"));

        // 添加目录，可以把目录下的文件都添加到暂存区
        git.add().addFilepattern(DigestUtil.md5Hex(userGroupName)).call();
        //提交
        git.commit().setMessage("新增配置").call();
        //推送到远程
        git.push().setRemote("origin").setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }
}
