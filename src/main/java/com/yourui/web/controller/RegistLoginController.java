package com.yourui.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.yourui.web.common.MessageResult;
import com.yourui.web.common.RedisOperator;
import com.yourui.web.common.RespMessage;
import com.yourui.web.config.Config;
import com.yourui.web.model.Users;
import com.yourui.web.model.UsersVo;
import com.yourui.web.service.UserService;
import com.yourui.web.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 登录注册
 * @author YI
 * @date 2018-7-19 15:58:19
 */
@Controller()
@RequestMapping("/users")
public class RegistLoginController {
    private static final Logger logger = LoggerFactory.getLogger(RegistLoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    public RedisOperator redis;

    /**
     * 用户注册
     * @param users
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public MessageResult regist(@RequestBody Users users){
        if (users == null || StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            return MessageResult.errorMsg("用户名或者密码不能为空");
        }

        boolean isExist = userService.queryUsernameIsExist(users.getUsername());
        if (isExist){
            return MessageResult.errorMsg("用户名已存在");
        }else {
            users.setPassword(DigestUtil.md5Hex(users.getPassword()));

            userService.saveUser(users);
        }

        List<Users> usersList = userService.queryUsernameAndPassWord(users.getUsername(), users.getPassword());

        UsersVo usersVo = setUserRedisSessionToken(usersList.get(0));
        usersVo.setPassword("");

        return MessageResult.ok(usersVo);
    }

    /**
     * 用户登录
     * @param users
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage login(@RequestBody Users users) {
        RespMessage respMessage = new RespMessage();
        if (users == null || StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())){
            respMessage.setCode(-1);
            respMessage.setMsg("用户名或者密码不能为空");
            logger.info("error : 用户名或者密码不能为空");
            return respMessage;
        }

        List<Users> usersList = userService.queryUsername(users.getUsername());
        if (usersList == null || usersList.size() == 0){
            respMessage.setCode(-1);
            respMessage.setMsg("用户不存在!");
            logger.info("error : 用户不存在");
            return respMessage;
        }

        Users user = usersList.get(0);
        if (!user.getPassword().equals(DigestUtil.md5Hex(users.getPassword()))){
            respMessage.setCode(-1);
            respMessage.setMsg("用户或密码不正确!");
            logger.info("error : 用户或密码不正确");
            return respMessage;
        }

        UsersVo usersVo = setUserRedisSessionToken(user);
        usersVo.setPassword("");
        respMessage.setData(usersVo);

        CookieUtil.addCookie("userToken", usersVo.getUserToken());
        CookieUtil.addCookie("userId", usersVo.getId());
        logger.info("userToken : " + usersVo.getUserToken());

        return respMessage;
    }

    /**
     * 用户注销
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public MessageResult logout(String userId){
        redis.del(Config.USER_REDIS_SESSION + ":" + userId);

        return MessageResult.ok();
    }

    /**
     * 把Token放入redis
     * @param users
     * @return
     */
    private UsersVo setUserRedisSessionToken(Users users){
        String token = SecureUtil.simpleUUID();

        redis.set(Config.USER_REDIS_SESSION + ":" + users.getId(), token, Config.REDIS_TIMEOUT);

        UsersVo usersVo = new UsersVo();

        BeanUtil.copyProperties(users, usersVo);
        usersVo.setUserToken(token);

        return usersVo;
    }
}
