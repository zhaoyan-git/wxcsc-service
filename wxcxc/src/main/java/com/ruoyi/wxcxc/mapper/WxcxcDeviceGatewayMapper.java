package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;

/**
 * 设备网关Mapper接口
 * 
 * @author l62202
 * @date 2021-10-31
 */
public interface WxcxcDeviceGatewayMapper 
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
     * 删除设备网关
     * 
     * @param id 设备网关主键
     * @return 结果
     */
    public int deleteWxcxcDeviceGatewayById(Long id);

    /**
     * 批量删除设备网关
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceGatewayByIds(Long[] ids);
}
