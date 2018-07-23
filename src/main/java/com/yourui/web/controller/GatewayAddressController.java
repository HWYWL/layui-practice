package com.yourui.web.controller;

import com.yourui.web.common.RespMessage;
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
