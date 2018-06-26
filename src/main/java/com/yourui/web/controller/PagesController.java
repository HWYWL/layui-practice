package com.yourui.web.controller;

import com.yourui.web.service.GameService;
import com.yourui.web.service.GatewayAddressService;
import com.yourui.web.service.RuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于界面显示
 * @author YI
 * @date 2018-6-19 14:45:17
 */
@Controller
public class PagesController {
    @Autowired
    GatewayAddressService gatewayAddressService;
    @Autowired
    RuleGroupService ruleGroupService;
    @Autowired
    GameService gameService;

    /**
     * 主页 网关页面
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 打开添加网关页面
     * @return
     */
    @RequestMapping("/addGatewayAddress")
    public String addGatewayAddress(){
        return "script/addGatewayAddress";
    }

    /**
     * 打开添加规则组页面
     * @return
     */
    @RequestMapping("/addRuleGroup")
    public String addRuleGroup(){
        return "script/addRuleGroup";
    }

    /**
     * 打开添加游戏页面
     * @return
     */
    @RequestMapping("/addGame")
    public String addGame(){
        return "script/addGame";
    }

    /**
     * 打开添加规则页面
     * @return
     */
    @RequestMapping("/addRules")
    public String addRules(){

        return "script/addRules";
    }

    /**
     * 打开添加用户组页面
     * @return
     */
    @RequestMapping("/addUserGroup")
    public String addUserGroup(){

        return "script/addUserGroup";
    }

    /**
     * 主页 网关页面
     * @return
     */
    @RequestMapping("/gatewayAddress")
    public String gatewayAddress(){
        return "index";
    }

    /**
     * 游戏管理页面
     * @return
     */
    @RequestMapping("/game")
    public String game(){
        return "script/game";
    }

    /**
     * 网关组管理页面
     * @return
     */
    @RequestMapping("/ruleGroup")
    public String ruleGroup(){
        return "script/ruleGroup";
    }

    /**
     * 用户组管理页面
     * @return
     */
    @RequestMapping("/userGroup")
    public String userGroup(){
        return "script/userGroup";
    }

    /**
     * 规则管理页面
     * @return
     */
    @RequestMapping("/rules")
    public String rules(){
        return "script/rules";
    }
}
