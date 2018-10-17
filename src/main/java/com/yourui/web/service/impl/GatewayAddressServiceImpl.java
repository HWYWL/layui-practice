package com.yourui.web.service.impl;

import com.yourui.web.dao.GatewayAddressMapper;
import com.yourui.web.model.GatewayAddress;
import com.yourui.web.model.GatewayAddressExample;
import com.yourui.web.common.Status;
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
public class GatewayAddressServiceImpl implements GatewayAddressService {

    @Autowired
    GatewayAddressMapper gatewayAddressMapper;

    @Override
    public List<GatewayAddress> fidnAll() {
        GatewayAddressExample example = new GatewayAddressExample();
        GatewayAddressExample.Criteria criteria = example.createCriteria();

        criteria.andDelEqualTo(Status.GREEN.getValue());

        return gatewayAddressMapper.selectByExample(example);
    }

    @Override
    public List<Long> fidnAllId(Byte del) {
        return gatewayAddressMapper.selectId(del);
    }

    @Override
    public GatewayAddress selectByPrimaryKey(Long id) {
        return gatewayAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Long id) {
        GatewayAddress gatewayAddress = new GatewayAddress();
        gatewayAddress.setId(id);
        gatewayAddress.setDel(Status.RED.getValue());

        return gatewayAddressMapper.updateByPrimaryKeySelective(gatewayAddress);
    }

    @Override
    public int updateByPrimaryKeySelective(GatewayAddress record) {
        return gatewayAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(GatewayAddress record) {
        return gatewayAddressMapper.insertSelective(record);
    }

    @Override
    public List<GatewayAddress> selectByIds(List<Long> ids) {
        GatewayAddressExample example = new GatewayAddressExample();
        GatewayAddressExample.Criteria criteria = example.createCriteria();

        criteria.andDelNotEqualTo(Status.RED.getValue());
        criteria.andIdIn(ids);

        return gatewayAddressMapper.selectByExample(example);
    }

    @Override
    public List<GatewayAddress> selectByIP(String ip) {
        GatewayAddressExample example = new GatewayAddressExample();
        GatewayAddressExample.Criteria criteria = example.createCriteria();

        criteria.andDelNotEqualTo(Status.RED.getValue());
        criteria.andInternalNetworkIpEqualTo(ip);

        return gatewayAddressMapper.selectByExample(example);
    }
}
