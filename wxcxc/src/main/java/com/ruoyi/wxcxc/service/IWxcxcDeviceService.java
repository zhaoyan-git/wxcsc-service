package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcDevice;

/**
 * 设备表Service接口
 * 
 * @author l62202
 * @date 2021-10-31
 */
public interface IWxcxcDeviceService 
{
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
     * 批量删除设备表
     * 
     * @param ids 需要删除的设备表主键集合
     * @return 结果
     */
    public int deleteWxcxcDeviceByIds(Long[] ids);

    /**
     * 删除设备表信息
     * 
     * @param id 设备表主键
     * @return 结果
     */
    public int deleteWxcxcDeviceById(Long id);
}
