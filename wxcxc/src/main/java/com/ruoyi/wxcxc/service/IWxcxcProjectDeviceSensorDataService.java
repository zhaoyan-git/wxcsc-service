package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import org.apache.ibatis.annotations.Param;

/**
 * 项目设备数据表Service接口
 * 
 * @author l62202
 * @date 2021-11-02
 */
public interface IWxcxcProjectDeviceSensorDataService 
{
    /**
     * 查询项目设备数据表
     * 
     * @param id 项目设备数据表主键
     * @return 项目设备数据表
     */
//    public WxcxcProjectDeviceSensorData selectWxcxcProjectDeviceSensorDataById(Long id);

    /**
     * 查询项目设备数据表列表
     * 
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 项目设备数据表集合
     */
//    public List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    /**
     * 新增项目设备数据表
     * 
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 结果
     */
//    public int insertWxcxcProjectDeviceSensorData(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    /**
     * 修改项目设备数据表
     * 
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 结果
     */
//    public int updateWxcxcProjectDeviceSensorData(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    /**
     * 批量删除项目设备数据表
     * 
     * @param ids 需要删除的项目设备数据表主键集合
     * @return 结果
     */
//    public int deleteWxcxcProjectDeviceSensorDataByIds(Long[] ids);

    /**
     * 删除项目设备数据表信息
     * 
     * @param id 项目设备数据表主键
     * @return 结果
     */
//    public int deleteWxcxcProjectDeviceSensorDataById(Long id);

    public List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(Long deviceId,WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    public int insertWxcxcProjectDeviceSensorData(Long deviceId,WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    int dropTable(Long deviceId);

    int createTable(Long deviceId);
}
