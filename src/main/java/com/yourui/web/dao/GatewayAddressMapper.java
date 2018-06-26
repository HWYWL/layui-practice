package com.yourui.web.dao;

import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.GatewayAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 网关地址
 * @author YI
 * @date 2018-6-19 12:36:42
 */
public interface GatewayAddressMapper {
    long countByExample(GatewayAddressExample example);

    int deleteByExample(GatewayAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GatewayAddress record);

    int insertSelective(GatewayAddress record);

    List<GatewayAddress> selectByExample(GatewayAddressExample example);

    List<Long> selectId(Byte del);

    GatewayAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GatewayAddress record, @Param("example") GatewayAddressExample example);

    int updateByExample(@Param("record") GatewayAddress record, @Param("example") GatewayAddressExample example);

    int updateByPrimaryKeySelective(GatewayAddress record);

    int updateByPrimaryKey(GatewayAddress record);
}