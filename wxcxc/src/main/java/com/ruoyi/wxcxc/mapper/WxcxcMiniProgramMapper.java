package com.ruoyi.wxcxc.mapper;

import org.apache.ibatis.annotations.Param;

public interface WxcxcMiniProgramMapper {

    // 获取项目结构物数
    int getProjectStructureCount(@Param("projectId") Long projectId);

    // 获取项目测点数
    int getProjectPointCount(@Param("projectId") Long projectId);

    // 获取项目DTO数
    int getProjectDtoCount(@Param("projectId") Long projectId);

    // 获取项目报警数
    int getProjectAlarmCount(@Param("projectId") Long projectId);

    // 获取企业测点数
    int getBusinessPointCount(@Param("businessId") Long businessId);

    // 根据企业成员ID获取项目结构物数
    int getProjectStructureCountByMemberId(@Param("memberId") Long memberId);

    // 根据企业成员ID获取项目测点数
    int getProjectPointCountByMemberId(@Param("memberId") Long memberId);

    // 根据企业成员ID获取项目设备数
    int getProjectDeviceCountByMemberId(@Param("memberId") Long memberId);

}
