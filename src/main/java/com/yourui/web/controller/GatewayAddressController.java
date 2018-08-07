package com.yourui.web.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yourui.web.common.RespMessage;
import com.yourui.web.config.Config;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.UserGroup;
import com.yourui.web.service.GatewayAddressService;
import com.yourui.web.service.UserGroupService;
import com.yourui.web.utils.GitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关地址接口
 * @author YI
 * @date 2018-6-19 16:36:45
 */
@Controller
@RequestMapping("/gatewayAddress")
public class GatewayAddressController {
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    UserGroupService userGroupService;

    /**
     * 获取所有网关有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage fidnAllGatewayAddress(){
        RespMessage respMessage = new RespMessage();
        List<GatewayAddress> gatewayAddressList = gatewayAddressService.fidnAll();
        GatewayAddress[] scriptInfos = new GatewayAddress[gatewayAddressList.size()];
        GatewayAddress[] infos = gatewayAddressList.toArray(scriptInfos);

        respMessage.setCount(gatewayAddressList.size());
        respMessage.setData(infos);

        return respMessage;
    }

    /**
     * 查看脚本
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detail(Long id) {
        ModelAndView modelAndView = new ModelAndView("script/code");
        RespMessage respMessage = new RespMessage();

        GatewayAddress gatewayAddress = gatewayAddressService.selectByPrimaryKey(id);
        respMessage.setData(gatewayAddress);

        modelAndView.addObject("message", respMessage);

        return modelAndView;
    }

    /**
     * 查看脚本 用于回显
     * @param id
     * @return
     */
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    @ResponseBody
    public RespMessage echo(Long id) {
        RespMessage respMessage = new RespMessage();

        GatewayAddress gatewayAddress = gatewayAddressService.selectByPrimaryKey(id);
        respMessage.setData(gatewayAddress);

        return respMessage;
    }

    /**
     * 按用网关执行
     * @param id
     * @return
     */
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage execute(Long id){
        List<UserGroup> userGroupList = userGroupService.fidnAll();
        List<UserGroup> group = new ArrayList<>();
        RespMessage respMessage = new RespMessage();

        if (userGroupList == null || userGroupList.size() == 0){
            respMessage.setCode(-1);
            respMessage.setMsg("用户组暂无数据！");

            return respMessage;
        }

        userGroupList.forEach(items ->{
            JSONArray gatewayAddressIdsArray = JSONUtil.parseArray(items.getGatewayAddressIds());
            List<Long> longs = JSONUtil.toList(gatewayAddressIdsArray, Long.class);
            if (longs.indexOf(id) != -1){
                group.add(items);
            }
        });

        if (group == null || group.size() == 0){
            respMessage.setCode(-1);
            respMessage.setMsg("此网关暂未启用！");

            return respMessage;
        }

        group.forEach(items ->{
            // 获取规则组所对应的网关信息
            JSONArray gatewayAddressIdsArray = JSONUtil.parseArray(items.getGatewayAddressIds());
            List<Long> longs = JSONUtil.toList(gatewayAddressIdsArray, Long.class);
            List<GatewayAddress> gatewayAddresses = gatewayAddressService.selectByIds(longs);
            String jsonStr = JSONUtil.toJsonStr(gatewayAddresses);

            // 生成密钥
            String key;
            if (items.getId() == null){
                key = RandomUtil.randomString(16);
            }else {
                UserGroup ug = userGroupService.selectByPrimaryKey(items.getId());
                key = ug.getEncryptKey();
            }

            AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());

            // 加密
            String encrypt = aes.encryptHex(jsonStr);

            String decrypt = aes.decryptStr(encrypt);

            System.out.println("加密数据：" + encrypt);
            System.out.println("解密数据：" + decrypt);
            System.out.println("key：" + key);

            items.setEncryptKey(key);
            try {
                // 把生成的文件push到git
                GitUtil.commitFiles(Config.GITEE_PROD_LOCALPATH, items.getUserGroupName(), encrypt);
                GitUtil.commitFiles(Config.GITHUB_PROD_LOCALPATH, items.getUserGroupName(), encrypt);
            } catch (Exception e) {
                respMessage.setCode(-1);
                respMessage.setMsg("提交git数据异常！");

                e.printStackTrace();
            }
        });

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


        gatewayAddressService.updateById(id);

        return respMessage;
    }

    /**
     * 保存网关信息
     * @param gatewayAddress
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespMessage save(@RequestBody GatewayAddress gatewayAddress){
        RespMessage respMessage = new RespMessage();
        if (gatewayAddress == null){
            respMessage.setMsg("id为空,更新数据失败!");
            respMessage.setCode(-1);

            return respMessage;
        }

        if (gatewayAddress.getId() == null){
            gatewayAddressService.insertSelective(gatewayAddress);
        }else {
            gatewayAddressService.updateByPrimaryKeySelective(gatewayAddress);
        }


        return respMessage;
    }
}
