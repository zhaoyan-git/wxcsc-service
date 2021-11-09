package com.ruoyi.wxcxc.mapper;

import org.apache.ibatis.annotations.Param;

public interface WxcxcConsoleMapper {

    // 获取发布项目数
    int getProjectCount(@Param("businessId")Long businessId);

    // 获取结构物数
    int getStructureCount(@Param("businessId")Long businessId);

    // 获取设备总数
    int getDeviceCount(@Param("businessId")Long businessId);

}
