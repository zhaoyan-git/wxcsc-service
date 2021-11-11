package com.ruoyi.wxcxc.mapper;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectPointData;
import org.apache.ibatis.annotations.Param;

/**
 * 测点数据表Mapper接口
 *
 * @author l62202
 * @date 2021-11-10
 */
public interface WxcxcProjectPointDataMapper {
    // 查询测点数据
    List<WxcxcProjectPointData> selectWxcxcProjectPointDataList(@Param("structureId") Long structureId, @Param("wxcxcProjectPointData") WxcxcProjectPointData wxcxcProjectPointData);

    // 新增测点数据
    int inserttWxcxcProjectPointData(@Param("structureId") Long structureId, @Param("wxcxcProjectPointData") WxcxcProjectPointData wxcxcProjectPointData);

    // 删除表
    int dropTable(@Param("structureId") Long structureId);

    // 创建表
    int createTable(@Param("structureId") Long structureId);

    // 获取数据数
    int getDataCount(@Param("structureId") Long structureId);
}
