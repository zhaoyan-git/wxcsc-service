package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceGatewayMapper;
import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;
import com.ruoyi.wxcxc.service.IWxcxcDeviceGatewayService;

/**
 * 设备网关Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-31
 */
@Service
public class WxcxcDeviceGatewayServiceImpl implements IWxcxcDeviceGatewayService 
{
    @Autowired
    private WxcxcDeviceGatewayMapper wxcxcDeviceGatewayMapper;

    /**
     * 查询设备网关
     * 
     * @param id 设备网关主键
     * @return 设备网关
     */
    @Override
    public WxcxcDeviceGateway selectWxcxcDeviceGatewayById(Long id)
    {
        return wxcxcDeviceGatewayMapper.selectWxcxcDeviceGatewayById(id);
    }

    /**
     * 查询设备网关列表
     * 
     * @param wxcxcDeviceGateway 设备网关
     * @return 设备网关
     */
    @Override
    public List<WxcxcDeviceGateway> selectWxcxcDeviceGatewayList(WxcxcDeviceGateway wxcxcDeviceGateway)
    {
        return wxcxcDeviceGatewayMapper.selectWxcxcDeviceGatewayList(wxcxcDeviceGateway);
    }

    /**
     * 新增设备网关
     * 
     * @param wxcxcDeviceGateway 设备网关
     * @return 结果
     */
    @Override
    public int insertWxcxcDeviceGateway(WxcxcDeviceGateway wxcxcDeviceGateway)
    {
        wxcxcDeviceGateway.setCreateTime(DateUtils.getNowDate());
        return wxcxcDeviceGatewayMapper.insertWxcxcDeviceGateway(wxcxcDeviceGateway);
    }

    /**
     * 修改设备网关
     * 
     * @param wxcxcDeviceGateway 设备网关
     * @return 结果
     */
    @Override
    public int updateWxcxcDeviceGateway(WxcxcDeviceGateway wxcxcDeviceGateway)
    {
        wxcxcDeviceGateway.setUpdateTime(DateUtils.getNowDate());
        return wxcxcDeviceGatewayMapper.updateWxcxcDeviceGateway(wxcxcDeviceGateway);
    }

    /**
     * 批量删除设备网关
     * 
     * @param ids 需要删除的设备网关主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDeviceGatewayByIds(Long[] ids)
    {
        return wxcxcDeviceGatewayMapper.deleteWxcxcDeviceGatewayByIds(ids);
    }

    /**
     * 删除设备网关信息
     * 
     * @param id 设备网关主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDeviceGatewayById(Long id)
    {
        return wxcxcDeviceGatewayMapper.deleteWxcxcDeviceGatewayById(id);
    }
}
