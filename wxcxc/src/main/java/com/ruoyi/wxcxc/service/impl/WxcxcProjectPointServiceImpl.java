package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectPoint;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointService;

/**
 * 项目测点Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcProjectPointServiceImpl implements IWxcxcProjectPointService 
{
    @Autowired
    private WxcxcProjectPointMapper wxcxcProjectPointMapper;

    /**
     * 查询项目测点
     * 
     * @param id 项目测点主键
     * @return 项目测点
     */
    @Override
    public WxcxcProjectPoint selectWxcxcProjectPointById(Long id)
    {
        return wxcxcProjectPointMapper.selectWxcxcProjectPointById(id);
    }

    /**
     * 查询项目测点列表
     * 
     * @param wxcxcProjectPoint 项目测点
     * @return 项目测点
     */
    @Override
    public List<WxcxcProjectPoint> selectWxcxcProjectPointList(WxcxcProjectPoint wxcxcProjectPoint)
    {
        return wxcxcProjectPointMapper.selectWxcxcProjectPointList(wxcxcProjectPoint);
    }

    /**
     * 新增项目测点
     * 
     * @param wxcxcProjectPoint 项目测点
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectPoint(WxcxcProjectPoint wxcxcProjectPoint)
    {
        wxcxcProjectPoint.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectPointMapper.insertWxcxcProjectPoint(wxcxcProjectPoint);
    }

    /**
     * 修改项目测点
     * 
     * @param wxcxcProjectPoint 项目测点
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectPoint(WxcxcProjectPoint wxcxcProjectPoint)
    {
        wxcxcProjectPoint.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectPointMapper.updateWxcxcProjectPoint(wxcxcProjectPoint);
    }

    /**
     * 批量删除项目测点
     * 
     * @param ids 需要删除的项目测点主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectPointByIds(Long[] ids)
    {
        return wxcxcProjectPointMapper.deleteWxcxcProjectPointByIds(ids);
    }

    /**
     * 删除项目测点信息
     * 
     * @param id 项目测点主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectPointById(Long id)
    {
        return wxcxcProjectPointMapper.deleteWxcxcProjectPointById(id);
    }
}
