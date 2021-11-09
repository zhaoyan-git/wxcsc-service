package com.ruoyi.wxcxc.mapper;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;

/**
 * 设备表Mapper接口
 *
 * @author l62202
 * @date 2021-10-31
 */
public interface WxcxcDeviceMapper {
    /**
     * 查询设备表
     *
     * @param id 设备表主键
     * @return 设备表
     */
    public WxcxcDevice selectWxcxcDeviceById(Long id);

    /**
     * 查询设备表列表
     *
     * @param wxcxcDevice 设备表
     * @return 设备表集合
     */
    public List<WxcxcDevice> selectWxcxcDeviceList(WxcxcDevice wxcxcDevice);

    /**
     * 新增设备表
     *
     * @param wxcxcDevice 设备表
     * @return 结果
     */
    public int insertWxcxcDevice(WxcxcDevice wxcxcDevice);

    /**
     * 修改设备表
     *
     * @param wxcxcDevice 设备表
     * @return 结果
     */
    public int updateWxcxcDevice(WxcxcDevice wxcxcDevice);

    /**
     * 删除设备表
     *
     * @param id 设备表主键
     * @return 结果
     */
    public int deleteWxcxcDeviceById(Long id);

    /**
     * 批量删除设备表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceByIds(Long[] ids);

    /**
     * 批量删除设备传感器
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceSensorByDeviceIds(Long[] ids);

    /**
     * 批量新增设备传感器
     *
     * @param wxcxcDeviceSensorList 设备传感器列表
     * @return 结果
     */
    public int batchWxcxcDeviceSensor(List<WxcxcDeviceSensor> wxcxcDeviceSensorList);


    /**
     * 通过设备表主键删除设备传感器信息
     *
     * @param id 设备表ID
     * @return 结果
     */
    public int deleteWxcxcDeviceSensorByDeviceId(Long id);

    List<WxcxcDevice> selectWxcxcDeviceListByBusinessId(Long businessId);
}
