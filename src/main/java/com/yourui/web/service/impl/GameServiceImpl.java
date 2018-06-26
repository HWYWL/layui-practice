package com.yourui.web.service.impl;

import com.yourui.web.common.Status;
import com.yourui.web.dao.GameMapper;
import com.yourui.web.dao.GatewayAddressMapper;
import com.yourui.web.model.Game;
import com.yourui.web.model.GameExample;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.GatewayAddressExample;
import com.yourui.web.service.GameService;
import com.yourui.web.service.GatewayAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * 网关地址逻辑实现
 * @author YI
 * @date 2018-6-19 16:41:30
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameMapper gameMapper;

    @Override
    public List<Game> fidnAll() {
        GameExample example = new GameExample();
        GameExample.Criteria criteria = example.createCriteria();

        criteria.andDelEqualTo(Status.GREEN.getValue());

        return gameMapper.selectByExample(example);
    }

    @Override
    public Game selectByPrimaryKey(Long id) {
        return gameMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Game> selectByIds(List<Long> ids) {
        GameExample example = new GameExample();
        GameExample.Criteria criteria = example.createCriteria();

        criteria.andDelNotEqualTo(Status.RED.getValue());
        criteria.andIdIn(ids);

        return gameMapper.selectByExample(example);
    }

    @Override
    public int updateById(Long id) {
        Game game = new Game();
        game.setId(id);
        game.setDel(Status.RED.getValue());

        return gameMapper.updateByPrimaryKeySelective(game);
    }

    @Override
    public int insertSelective(Game record) {
        return gameMapper.insertSelective(record);
    }
}
