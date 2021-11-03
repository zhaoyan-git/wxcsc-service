package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectDeviceSensorDataMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import com.ruoyi.wxcxc.service.IWxcxcProjectDeviceSensorDataService;

/**
 * 项目设备数据表Service业务层处理
 *
 * @author l62202
 * @date 2021-11-02
 */
@Service
public class WxcxcProjectDeviceSensorDataServiceImpl implements IWxcxcProjectDeviceSensorDataService {
    @Autowired
    private WxcxcProjectDeviceSensorDataMapper wxcxcProjectDeviceSensorDataMapper;

    /**
     * 查询项目设备数据表
     *
     * @param id 项目设备数据表主键
     * @return 项目设备数据表
     */
//    @Override
//    public WxcxcProjectDeviceSensorData selectWxcxcProjectDeviceSensorDataById(Long id)
//    {
//        return wxcxcProjectDeviceSensorDataMapper.selectWxcxcProjectDeviceSensorDataById(id);
//    }

    /**
     * 查询项目设备数据表列表
     *
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 项目设备数据表
     */
//    @Override
//    public List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        return wxcxcProjectDeviceSensorDataMapper.selectWxcxcProjectDeviceSensorDataList(wxcxcProjectDeviceSensorData);
//    }

    /**
     * 新增项目设备数据表
     *
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 结果
     */
//    @Override
//    public int insertWxcxcProjectDeviceSensorData(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        wxcxcProjectDeviceSensorData.setCreateTime(DateUtils.getNowDate());
//        return wxcxcProjectDeviceSensorDataMapper.insertWxcxcProjectDeviceSensorData(wxcxcProjectDeviceSensorData);
//    }

    /**
     * 修改项目设备数据表
     *
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 结果
     */
//    @Override
//    public int updateWxcxcProjectDeviceSensorData(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        return wxcxcProjectDeviceSensorDataMapper.updateWxcxcProjectDeviceSensorData(wxcxcProjectDeviceSensorData);
//    }

    /**
     * 批量删除项目设备数据表
     *
     * @param ids 需要删除的项目设备数据表主键
     * @return 结果
     */
//    @Override
//    public int deleteWxcxcProjectDeviceSensorDataByIds(Long[] ids)
//    {
//        return wxcxcProjectDeviceSensorDataMapper.deleteWxcxcProjectDeviceSensorDataByIds(ids);
//    }

    /**
     * 删除项目设备数据表信息
     *
     * @param id 项目设备数据表主键
     * @return 结果
     */
//    @Override
//    public int deleteWxcxcProjectDeviceSensorDataById(Long id)
//    {
//        return wxcxcProjectDeviceSensorDataMapper.deleteWxcxcProjectDeviceSensorDataById(id);
//    }
    @Override
    public List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(Long deviceId, WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData) {
        return wxcxcProjectDeviceSensorDataMapper.selectWxcxcProjectDeviceSensorDataList(deviceId, wxcxcProjectDeviceSensorData);
    }

    @Override
    public int insertWxcxcProjectDeviceSensorData(Long deviceId, WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData) {
        return wxcxcProjectDeviceSensorDataMapper.insertWxcxcProjectDeviceSensorData(deviceId, wxcxcProjectDeviceSensorData);
    }

    @Override
    public int dropTable(Long deviceId) {
        return wxcxcProjectDeviceSensorDataMapper.dropTable(deviceId);
    }

    @Override
    public int createTable(Long deviceId) {
        return wxcxcProjectDeviceSensorDataMapper.createTable(deviceId);
    }
}
