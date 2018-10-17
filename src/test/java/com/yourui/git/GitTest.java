package com.yourui.git;

import cn.hutool.core.io.FileUtil;
import com.yourui.web.utils.GitUtil;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GitTest {
    //远程库路径
    public static final String remotePath = "https://gitee.com/hwyhy/sg.git";
    //下载已有仓库到本地路径
    public static final String localPath = "git";
    //远程服务器上的用户名
    public static final String username = "1045149087@qq.com";
    //远程服务器上的密码
    public static final String password = "123456yi";

    /**
     * 克隆远程库
     * @throws GitAPIException
     */
    @Test
    public void testClone() throws GitAPIException {
        try {
            GitUtil.gitClone(localPath, remotePath, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件并提交到git上
     */
    @Test
    public void commitFiles() throws IOException, GitAPIException{
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider(username, password);
        File file = FileUtil.file(localPath, "config.txt");
        FileUtil.appendUtf8String("新增配置 vvvv", file);
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));

        // 添加目录，可以把目录下的文件都添加到暂存区
        git.add().addFilepattern("config.txt").call();
        //提交
        git.commit().setMessage("新增配置").call();
        //推送到远程
        git.push().setRemote("origin").setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }
}
