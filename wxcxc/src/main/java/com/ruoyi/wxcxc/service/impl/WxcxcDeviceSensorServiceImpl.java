package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceSensorMapper;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.service.IWxcxcDeviceSensorService;

/**
 * 设备传感器Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-31
 */
@Service
public class WxcxcDeviceSensorServiceImpl implements IWxcxcDeviceSensorService 
{
    @Autowired
    private WxcxcDeviceSensorMapper wxcxcDeviceSensorMapper;

    /**
     * 查询设备传感器
     * 
     * @param id 设备传感器主键
     * @return 设备传感器
     */
    @Override
    public WxcxcDeviceSensor selectWxcxcDeviceSensorById(Long id)
    {
        return wxcxcDeviceSensorMapper.selectWxcxcDeviceSensorById(id);
    }

    /**
     * 查询设备传感器列表
     * 
     * @param wxcxcDeviceSensor 设备传感器
     * @return 设备传感器
     */
    @Override
    public List<WxcxcDeviceSensor> selectWxcxcDeviceSensorList(WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        return wxcxcDeviceSensorMapper.selectWxcxcDeviceSensorList(wxcxcDeviceSensor);
    }

    /**
     * 新增设备传感器
     * 
     * @param wxcxcDeviceSensor 设备传感器
     * @return 结果
     */
    @Override
    public int insertWxcxcDeviceSensor(WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        wxcxcDeviceSensor.setCreateTime(DateUtils.getNowDate());
        return wxcxcDeviceSensorMapper.insertWxcxcDeviceSensor(wxcxcDeviceSensor);
    }

    /**
     * 修改设备传感器
     * 
     * @param wxcxcDeviceSensor 设备传感器
     * @return 结果
     */
    @Override
    public int updateWxcxcDeviceSensor(WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        wxcxcDeviceSensor.setUpdateTime(DateUtils.getNowDate());
        return wxcxcDeviceSensorMapper.updateWxcxcDeviceSensor(wxcxcDeviceSensor);
    }

    /**
     * 批量删除设备传感器
     * 
     * @param ids 需要删除的设备传感器主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDeviceSensorByIds(Long[] ids)
    {
        return wxcxcDeviceSensorMapper.deleteWxcxcDeviceSensorByIds(ids);
    }

    /**
     * 删除设备传感器信息
     * 
     * @param id 设备传感器主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDeviceSensorById(Long id)
    {
        return wxcxcDeviceSensorMapper.deleteWxcxcDeviceSensorById(id);
    }
}
