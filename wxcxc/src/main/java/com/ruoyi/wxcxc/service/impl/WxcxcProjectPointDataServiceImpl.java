package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointDataMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectPointData;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointDataService;

/**
 * 测点数据表Service业务层处理
 *
 * @author l62202
 * @date 2021-11-10
 */
@Service
public class WxcxcProjectPointDataServiceImpl implements IWxcxcProjectPointDataService {
    @Autowired
    private WxcxcProjectPointDataMapper wxcxcProjectPointDataMapper;

    // 查询测点数据
    @Override
    public List<WxcxcProjectPointData> selectWxcxcProjectPointDataList(@Param("structureId") Long structureId, WxcxcProjectPointData wxcxcProjectPointData) {
        return wxcxcProjectPointDataMapper.selectWxcxcProjectPointDataList(structureId, wxcxcProjectPointData);
    }

    // 新增测点数据
    @Override
    public int inserttWxcxcProjectPointData(@Param("structureId") Long structureId, WxcxcProjectPointData wxcxcProjectPointData) {
        return wxcxcProjectPointDataMapper.inserttWxcxcProjectPointData(structureId, wxcxcProjectPointData);
    }

    // 删除表
    @Override
    public int dropTable(@Param("structureId") Long structureId) {
        return wxcxcProjectPointDataMapper.dropTable(structureId);
    }

    // 创建表
    @Override
    public int createTable(@Param("structureId") Long structureId) {
        return wxcxcProjectPointDataMapper.createTable(structureId);
    }

    // 获取数据数
    @Override
    public int getDataCount(@Param("structureId") Long structureId) {
        return wxcxcProjectPointDataMapper.getDataCount(structureId);
    }
}
