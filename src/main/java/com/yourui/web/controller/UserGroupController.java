package com.yourui.web.controller;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.RespMessage;
import com.yourui.web.config.Config;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.RuleGroup;
import com.yourui.web.model.UserGroup;
import com.yourui.web.service.*;
import com.yourui.web.utils.GitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 用户组接口
 * @author YI
 * @date 2018-6-20 09:16:41
 */
@Controller
@RequestMapping("/userGroup")
public class UserGroupController {
    private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    RulesService rulesService;
    @Autowired
    CommonService commonService;

    /**
     * 获取所有组信息有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage fidnAllGatewayAddress(){
        RespMessage respMessage = new RespMessage();

        List<UserGroup> userGroups = userGroupService.fidnAll();
        respMessage.setData(userGroups);

        return respMessage;
    }

    /**
     * 获取服务器转发规则,此接口用于go网关启动获取
     * @param ip 网关内网ip
     * @return
     */
    @RequestMapping(value = "/findByRuleGroup", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage findByRuleGroup(String ip){

        return commonService.findByRuleGroupIP(ip);
    }

    /**
     * 查看规则组包含的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detail(Long id) {
        ModelAndView modelAndView = new ModelAndView("script/code");
        RespMessage respMessage = new RespMessage();

        if (id == null){
            respMessage.setMsg("id为空,查询数据失败!");
            respMessage.setCode(-1);
            modelAndView.addObject("message", respMessage);

            return modelAndView;
        }

        UserGroup userGroup = userGroupService.selectByPrimaryKey(id);
        String ruleGroupIds = userGroup.getRuleGroupIds();
        if(ruleGroupIds == null || ruleGroupIds.isEmpty()){
            respMessage.setMsg("规则组不存在!");
            respMessage.setCode(-1);
            modelAndView.addObject("message", respMessage);

            return modelAndView;
        }

        StringBuffer buffer = new StringBuffer();

        RuleGroup ruleGroup = ruleGroupService.selectByPrimaryKey(Long.valueOf(ruleGroupIds));
        buffer.append("所属规则组:" + ruleGroup.getRuleGroupName() + "\r\n\n");

        String gatewayAddressIds = userGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

        if (ids == null || ids.size() == 0){
            respMessage.setMsg("此规则组没有关联网关!");
            respMessage.setData("此规则组没有关联网关!");
            modelAndView.addObject("message", respMessage);

            return modelAndView;
        }

        List<GatewayAddress> gatewayAddress = gatewayAddressService.selectByIds(ids);

        // 获取网关信息
        gatewayAddress.forEach(item-> buffer.append(item.getGatewayAddressName() + "\r\n"));
        buffer.append("key:" + userGroup.getEncryptKey() + "\r\n\n");

        StringBuffer giteePath = new StringBuffer();
        giteePath.append(GitUtil.gitFilePath(Config.GITEE_PROD_LOCALPATH));
        giteePath.append(CharUtil.SLASH);
        giteePath.append(DigestUtil.md5Hex(userGroup.getUserGroupName()));
        giteePath.append(CharUtil.SLASH);
        giteePath.append(DigestUtil.md5Hex(userGroup.getUserGroupName()));
        buffer.append("下载地址:" + giteePath.toString() + "\r\n\n");

        StringBuffer githubPath = new StringBuffer();
        githubPath.append(GitUtil.gitFilePath(Config.GITHUB_PROD_LOCALPATH));
        githubPath.append(CharUtil.SLASH);
        githubPath.append(DigestUtil.md5Hex(userGroup.getUserGroupName()));
        githubPath.append(CharUtil.SLASH);
        githubPath.append(DigestUtil.md5Hex(userGroup.getUserGroupName()));
        buffer.append("下载地址:" + githubPath.toString() + "\r\n\n");

        respMessage.setData(buffer.toString());

        modelAndView.addObject("message", respMessage);

        return modelAndView;
    }

    /**
     * 查看规则组包含的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage echo(Long id) {
        RespMessage respMessage = new RespMessage();
        Map<String, Object> map = new HashMap<>(16);
        UserGroup userGroup = userGroupService.selectByPrimaryKey(id);
        String ruleGroupIds = userGroup.getRuleGroupIds();
        // 可用网关
        List<GatewayAddress> usableGatewayAddresses = commonService.detailGatewayAddress(Long.valueOf(ruleGroupIds));

        String gatewayAddressIds = userGroup.getGatewayAddressIds();
        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> ids = JSONUtil.toList(jsonArray, Long.class);

        // 已用网关
        List<GatewayAddress> usedGatewayAddresses = gatewayAddressService.selectByIds(ids);

        map.put("usableGatewayAddresses", usableGatewayAddresses);
        map.put("usedGatewayAddresses", usedGatewayAddresses);
        map.put("userGroup", userGroup);

        respMessage.setData(map);

        return respMessage;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delID", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage delID(Long id){
        RespMessage respMessage = new RespMessage();
        if (id == null){
            respMessage.setMsg("id为空,更新数据失败!");
            respMessage.setCode(-1);

            return respMessage;
        }

        userGroupService.updateById(id);

        return respMessage;
    }

    /**
     * 保存规则组信息
     * @param userGroup
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage save(String userGroup, String items){
        RespMessage respMessage = new RespMessage();

        if (StrUtil.isBlank(userGroup)){
            respMessage.setMsg("请选择规则组");
            respMessage.setCode(-1);

            return respMessage;
        }

        if (StrUtil.isBlank(items) || items.equals("[]")){
            respMessage.setMsg("防御网关不存在,请选择有防御网关的规则组!");
            respMessage.setCode(-1);
            logger.info("防御网关不存在,请选择有防御网关的规则组!");

            return respMessage;
        }

        UserGroup group = JSONUtil.toBean(userGroup, UserGroup.class);
        group.setGatewayAddressIds(items);

        // 获取规则组所对应的网关信息
        JSONArray gatewayAddressIdsArray = JSONUtil.parseArray(items);
        List<Long> longs = JSONUtil.toList(gatewayAddressIdsArray, Long.class);
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(longs);
        String jsonStr = JSONUtil.toJsonStr(gatewayAddresses);

        // 生成密钥
        String key;
        if (group.getId() == null){
            key = RandomUtil.randomString(16);
        }else {
            UserGroup ug = userGroupService.selectByPrimaryKey(group.getId());
            key = ug.getEncryptKey();
        }

        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());

        // 加密
        String encrypt = aes.encryptHex(jsonStr);

        String decrypt = aes.decryptStr(encrypt);

        System.out.println("加密数据：" + encrypt);
        System.out.println("解密数据：" + decrypt);
        System.out.println("key：" + key);

        group.setEncryptKey(key);
        try {
            // 把生成的文件push到git
            GitUtil.commitFiles(Config.GITEE_PROD_LOCALPATH, group.getUserGroupName(), encrypt);
            GitUtil.commitFiles(Config.GITHUB_PROD_LOCALPATH, group.getUserGroupName(), encrypt);
        } catch (Exception e) {
            respMessage.setCode(-1);
            respMessage.setData(e.getCause().toString());
            e.printStackTrace();
        }

        if (group.getId() == null) {
            userGroupService.insertSelective(group);
        }else {
            userGroupService.updateByPrimaryKeySelective(group);
        }

        return respMessage;
    }

    /**
     * 按用户组执行
     * @param id
     * @return
     */
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage execute(Long id){
        RespMessage respMessage = new RespMessage();

        UserGroup userGroup = userGroupService.selectByPrimaryKey(id);

        // 获取网关IP地址
        String gatewayAddressIds = userGroup.getGatewayAddressIds();
        if (gatewayAddressIds == null || gatewayAddressIds.length() == 0){
            respMessage.setCode(-1);
            respMessage.setData("网关IP不存在");
            logger.info("网关IP不存在");

            return respMessage;
        }

        JSONArray jsonArray = JSONUtil.parseArray(gatewayAddressIds);
        List<Long> longs = JSONUtil.toList(jsonArray, Long.class);
        List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(longs);

        List<String> arrayList = new ArrayList<>();

        gatewayAddresses.forEach(item -> arrayList.add(item.getOutsideNetworkIp()));

        // 获取一个随机网关IP用于游戏
        Random random = new Random();
        String ip = arrayList.get(random.nextInt(arrayList.size()));

        Map<String, Object> map = new HashMap<>(16);
        map.put("gatewayIp", ip);

        try {
            // 把网关ip和游戏ip发送到防御网关
            gatewayAddresses.forEach(item->
                    HttpUtil.createPost(Config.ADDRESS_HTTP + item.getOutsideNetworkIp() + Config.ADDRESS_PORT)
                            .charset(CharsetUtil.UTF_8)
                            .timeout(2000)
                            .setEncodeUrl(false)
                            .body(JSONUtil.toJsonPrettyStr(map))
                            .execute());
            respMessage.setData("执行成功");
        }catch (Exception e){
            respMessage.setCode(-1);
            respMessage.setData(e.getCause().toString());
            logger.info("用户组把网关ip和游戏ip发送到防御网关：" + e.getCause());
            e.printStackTrace();
        }

        return respMessage;
    }

}
