package com.yourui.web.service;

import com.yourui.web.model.Users;

import java.util.List;

/**
 * 用户操作实现类
 * @author YI
 * @date 2018-7-19 16:08:12
 */
public interface UserService {
    /**
     * 查询用户是否存在
     * @param username 用户名
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 保存用户(用户注册)
     * @param user
     */
    void saveUser(Users user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    List<Users> queryUsername(String username);

    /**
     * 通过同户名和密码查找用户
     * @param username  用户名
     * @param password  密码
     * @return
     */
    List<Users> queryUsernameAndPassWord(String username, String password);
}
