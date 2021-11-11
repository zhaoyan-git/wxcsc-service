package com.ruoyi.wxcxc.mapper;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import org.apache.ibatis.annotations.Param;

/**
 * 项目设备数据表Mapper接口
 *
 * @author l62202
 * @date 2021-11-02
 */
public interface WxcxcProjectDeviceSensorDataMapper {
    /**
     * 查询项目设备数据表列表
     *
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 项目设备数据表集合
     */
    public List<WxcxcProjectDeviceSensorData> selectWxcxcProjectDeviceSensorDataList(@Param("deviceId") Long deviceId, @Param("wxcxcProjectDeviceSensorData") WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);

    /**
     * 新增项目设备数据表
     *
     * @param wxcxcProjectDeviceSensorData 项目设备数据表
     * @return 结果
     */
    public int insertWxcxcProjectDeviceSensorData(@Param("deviceId") Long deviceId, @Param("wxcxcProjectDeviceSensorData") WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData);


    /**
     * 删除表
     *
     * @param deviceId
     * @return
     */
    int dropTable(@Param("deviceId") Long deviceId);

    /**
     * 创建表
     *
     * @param deviceId
     * @return
     */
    int createTable(@Param("deviceId") Long deviceId);

    // 获取数据数
    int getDataCount(@Param("deviceId") Long deviceId);
}
