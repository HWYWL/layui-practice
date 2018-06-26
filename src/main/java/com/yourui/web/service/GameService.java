package com.yourui.web.service;

import com.yourui.web.model.Game;

import java.util.List;

/**
 * 游戏服务类
 * @author YI
 * @date 2018-6-20 10:49:38
 */
public interface GameService {
    /**
     * 查询所有有效数据
     * @return
     */
    List<Game> fidnAll();

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    Game selectByPrimaryKey(Long id);

    /**
     * 查询游戏
     * @param ids
     * @return
     */
    List<Game> selectByIds(List<Long> ids);

    /**
     * 更新数据
     * @param id
     * @return
     */
    int updateById(Long id);

    /**
     * 保存数据
     * @param record
     * @return
     */
    int insertSelective(Game record);
}
