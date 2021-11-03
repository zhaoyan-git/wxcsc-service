package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;

/**
 * 设备传感器Service接口
 * 
 * @author l62202
 * @date 2021-10-31
 */
public interface IWxcxcDeviceSensorService 
{
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
     * 批量删除设备传感器
     * 
     * @param ids 需要删除的设备传感器主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceSensorByIds(Long[] ids);

    /**
     * 删除设备传感器信息
     * 
     * @param id 设备传感器主键
     * @return 结果
     */
    public int deleteWxcxcDeviceSensorById(Long id);
}
