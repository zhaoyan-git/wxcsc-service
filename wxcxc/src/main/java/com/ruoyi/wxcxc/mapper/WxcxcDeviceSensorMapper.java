package com.ruoyi.wxcxc.mapper;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import org.apache.ibatis.annotations.Param;

/**
 * 设备传感器Mapper接口
 *
 * @author l62202
 * @date 2021-10-31
 */
public interface WxcxcDeviceSensorMapper {
    /**
     * 查询设备传感器
     *
     * @param id 设备传感器主键
     * @return 设备传感器
     */
    public WxcxcDeviceSensor selectWxcxcDeviceSensorById(Long id);

    /**
     * 查询设备传感器列表
     *
     * @param wxcxcDeviceSensor 设备传感器
     * @return 设备传感器集合
     */
    public List<WxcxcDeviceSensor> selectWxcxcDeviceSensorList(WxcxcDeviceSensor wxcxcDeviceSensor);

    /**
     * 新增设备传感器
     *
     * @param wxcxcDeviceSensor 设备传感器
     * @return 结果
     */
    public int insertWxcxcDeviceSensor(WxcxcDeviceSensor wxcxcDeviceSensor);

    /**
     * 修改设备传感器
     *
     * @param wxcxcDeviceSensor 设备传感器
     * @return 结果
     */
    public int updateWxcxcDeviceSensor(WxcxcDeviceSensor wxcxcDeviceSensor);

    /**
     * 删除设备传感器
     *
     * @param id 设备传感器主键
     * @return 结果
     */
    public int deleteWxcxcDeviceSensorById(Long id);

    /**
     * 批量删除设备传感器
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceSensorByIds(Long[] ids);

    // 根据从站地址和网关Id获取传感器
    List<WxcxcDeviceSensor> selectWxcxcDeviceSensorByDtuAndSlaveUnitId(@Param("slaveUnitId") String slaveUnitId, @Param("gateWayId") Long gateWayId);

    List<String> selectSlaveUnitIdByGatewayId(@Param("gatewayId") Long gatewayId);
}
