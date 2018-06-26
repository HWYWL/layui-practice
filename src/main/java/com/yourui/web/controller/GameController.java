package com.yourui.web.controller;

import com.yourui.web.common.Message;
import com.yourui.web.model.Game;
import com.yourui.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 游戏接口
 * @author YI
 * @date 2018-6-20 10:48:58
 */
@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;

    /**
     * 获取所有游戏有效数据
     * @return
     */
    @RequestMapping(value = "/fidnAll", method = RequestMethod.GET)
    @ResponseBody
    public Message fidnAllGatewayAddress(){
        Message message = new Message();
        List<Game> games = gameService.fidnAll();
        Game[] gameInfos = new Game[games.size()];
        Game[] infos = games.toArray(gameInfos);

        message.setCount(games.size());
        message.setData(infos);

        return message;
    }

    /**
     * 查看游戏详细数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detail(Long id) {
        ModelAndView modelAndView = new ModelAndView("script/code");
        Message message = new Message();

        Game gatewayAddress = gameService.selectByPrimaryKey(id);
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

        gameService.updateById(id);

        return message;
    }

    /**
     * 保存游戏信息
     * @param game
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Message save(@RequestBody Game game){
        Message message = new Message();
        if (game == null){
            message.setMsg("id为空,更新数据失败!");
            message.setCode(-1);

            return message;
        }

        gameService.insertSelective(game);

        return message;
    }
}
