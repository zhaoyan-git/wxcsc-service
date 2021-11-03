package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wxcxc.service.IWxcxcProjectDeviceSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.mapper.WxcxcDeviceMapper;
import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.service.IWxcxcDeviceService;

/**
 * 设备表Service业务层处理
 *
 * @author l62202
 * @date 2021-10-31
 */
@Service
public class WxcxcDeviceServiceImpl implements IWxcxcDeviceService {
    @Autowired
    private WxcxcDeviceMapper wxcxcDeviceMapper;
    @Autowired
    private IWxcxcProjectDeviceSensorDataService wxcxcProjectDeviceSensorDataService;

    /**
     * 查询设备表
     *
     * @param id 设备表主键
     * @return 设备表
     */
    @Override
    public WxcxcDevice selectWxcxcDeviceById(Long id) {
        return wxcxcDeviceMapper.selectWxcxcDeviceById(id);
    }

    /**
     * 查询设备表列表
     *
     * @param wxcxcDevice 设备表
     * @return 设备表
     */
    @Override
    public List<WxcxcDevice> selectWxcxcDeviceList(WxcxcDevice wxcxcDevice) {
        return wxcxcDeviceMapper.selectWxcxcDeviceList(wxcxcDevice);
    }

    /**
     * 新增设备表
     *
     * @param wxcxcDevice 设备表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWxcxcDevice(WxcxcDevice wxcxcDevice) {
        wxcxcDevice.setCreateTime(DateUtils.getNowDate());
        int rows = wxcxcDeviceMapper.insertWxcxcDevice(wxcxcDevice);
        insertWxcxcDeviceSensor(wxcxcDevice);

        wxcxcProjectDeviceSensorDataService.createTable(wxcxcDevice.getId());

        return rows;
    }

    /**
     * 修改设备表
     *
     * @param wxcxcDevice 设备表
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWxcxcDevice(WxcxcDevice wxcxcDevice) {
        wxcxcDevice.setUpdateTime(DateUtils.getNowDate());
        wxcxcDeviceMapper.deleteWxcxcDeviceSensorByDeviceId(wxcxcDevice.getId());
        insertWxcxcDeviceSensor(wxcxcDevice);
        return wxcxcDeviceMapper.updateWxcxcDevice(wxcxcDevice);
    }

    /**
     * 批量删除设备表
     *
     * @param ids 需要删除的设备表主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWxcxcDeviceByIds(Long[] ids) {
        wxcxcDeviceMapper.deleteWxcxcDeviceSensorByDeviceIds(ids);
        return wxcxcDeviceMapper.deleteWxcxcDeviceByIds(ids);
    }

    /**
     * 删除设备表信息
     *
     * @param id 设备表主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcDeviceById(Long id) {
        wxcxcDeviceMapper.deleteWxcxcDeviceSensorByDeviceId(id);
        return wxcxcDeviceMapper.deleteWxcxcDeviceById(id);
    }

    /**
     * 新增设备传感器信息
     *
     * @param wxcxcDevice 设备表对象
     */
    public void insertWxcxcDeviceSensor(WxcxcDevice wxcxcDevice) {
        List<WxcxcDeviceSensor> wxcxcDeviceSensorList = wxcxcDevice.getWxcxcDeviceSensorList();
        Long id = wxcxcDevice.getId();
        if (StringUtils.isNotNull(wxcxcDeviceSensorList)) {
            List<WxcxcDeviceSensor> list = new ArrayList<WxcxcDeviceSensor>();
            for (WxcxcDeviceSensor wxcxcDeviceSensor : wxcxcDeviceSensorList) {
                wxcxcDeviceSensor.setDeviceId(id);
                list.add(wxcxcDeviceSensor);
            }
            if (list.size() > 0) {
                wxcxcDeviceMapper.batchWxcxcDeviceSensor(list);
            }
        }
    }
}
