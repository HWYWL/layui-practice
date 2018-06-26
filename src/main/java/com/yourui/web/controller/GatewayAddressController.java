package com.yourui.web.controller;

import com.yourui.web.common.Message;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.service.GatewayAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 获取所有网关有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public Message fidnAllGatewayAddress(){
        Message message = new Message();
        List<GatewayAddress> gatewayAddressList = gatewayAddressService.fidnAll();
        GatewayAddress[] scriptInfos = new GatewayAddress[gatewayAddressList.size()];
        GatewayAddress[] infos = gatewayAddressList.toArray(scriptInfos);

        message.setCount(gatewayAddressList.size());
        message.setData(infos);

        return message;
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
        Message message = new Message();

        GatewayAddress gatewayAddress = gatewayAddressService.selectByPrimaryKey(id);
        message.setData(gatewayAddress);

        modelAndView.addObject("message", message);

        return modelAndView;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delID", method = RequestMethod.POST)
    @ResponseBody
    public Message delID(Long id){
        Message message = new Message();
        if (id == null){
            message.setMsg("id为空,更新数据失败!");
            message.setCode(-1);

            return message;
        }


        gatewayAddressService.updateById(id);

        return message;
    }

    /**
     * 保存网关信息
     * @param gatewayAddress
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Message save(@RequestBody GatewayAddress gatewayAddress){
        Message message = new Message();
        if (gatewayAddress == null){
            message.setMsg("id为空,更新数据失败!");
            message.setCode(-1);

            return message;
        }

        gatewayAddressService.insertSelective(gatewayAddress);

        return message;
    }
}
