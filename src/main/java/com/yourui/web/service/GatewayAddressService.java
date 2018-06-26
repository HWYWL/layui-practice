package com.yourui.web.service;

import com.yourui.web.model.GatewayAddress;

import java.util.List;

/**
 * 网关服务类
 * @author YI
 * @date 2018-6-19 16:39:19
 */
public interface GatewayAddressService {
    /**
     * 查询所有有效数据
     * @return
     */
    List<GatewayAddress> fidnAll();

    /**
     * 查询所有有效数据id
     * @return
     */
    List<Long> fidnAllId(Byte del);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    GatewayAddress selectByPrimaryKey(Long id);

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
    int insertSelective(GatewayAddress record);

    /**
     * 查询防御网关
     * @param ids
     * @return
     */
    List<GatewayAddress> selectByIds(List<Long> ids);
}
