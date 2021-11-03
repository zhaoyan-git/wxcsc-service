package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;

/**
 * 设备网关Service接口
 * 
 * @author l62202
 * @date 2021-10-31
 */
public interface IWxcxcDeviceGatewayService 
{
    /**
     * 查询设备网关
     * 
     * @param id 设备网关主键
     * @return 设备网关
     */
    public WxcxcDeviceGateway selectWxcxcDeviceGatewayById(Long id);

    /**
     * 查询设备网关列表
     * 
     * @param wxcxcDeviceGateway 设备网关
     * @return 设备网关集合
     */
    public List<WxcxcDeviceGateway> selectWxcxcDeviceGatewayList(WxcxcDeviceGateway wxcxcDeviceGateway);

    /**
     * 新增设备网关
     * 
     * @param wxcxcDeviceGateway 设备网关
     * @return 结果
     */
    public int insertWxcxcDeviceGateway(WxcxcDeviceGateway wxcxcDeviceGateway);

    /**
     * 修改设备网关
     * 
     * @param wxcxcDeviceGateway 设备网关
     * @return 结果
     */
    public int updateWxcxcDeviceGateway(WxcxcDeviceGateway wxcxcDeviceGateway);

    /**
     * 批量删除设备网关
     * 
     * @param ids 需要删除的设备网关主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceGatewayByIds(Long[] ids);

    /**
     * 删除设备网关信息
     * 
     * @param id 设备网关主键
     * @return 结果
     */
    public int deleteWxcxcDeviceGatewayById(Long id);
}
