package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectPoint;

/**
 * 项目测点Service接口
 * 
 * @author l62202
 * @date 2021-11-01
 */
public interface IWxcxcProjectPointService 
{
    /**
     * 查询项目测点
     * 
     * @param id 项目测点主键
     * @return 项目测点
     */
    public WxcxcProjectPoint selectWxcxcProjectPointById(Long id);

    /**
     * 查询项目测点列表
     * 
     * @param wxcxcProjectPoint 项目测点
     * @return 项目测点集合
     */
    public List<WxcxcProjectPoint> selectWxcxcProjectPointList(WxcxcProjectPoint wxcxcProjectPoint);

    /**
     * 新增项目测点
     * 
     * @param wxcxcProjectPoint 项目测点
     * @return 结果
     */
    public int insertWxcxcProjectPoint(WxcxcProjectPoint wxcxcProjectPoint);

    /**
     * 修改项目测点
     * 
     * @param wxcxcProjectPoint 项目测点
     * @return 结果
     */
    public int updateWxcxcProjectPoint(WxcxcProjectPoint wxcxcProjectPoint);

    /**
     * 批量删除项目测点
     * 
     * @param ids 需要删除的项目测点主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectPointByIds(Long[] ids);

    /**
     * 删除项目测点信息
     * 
     * @param id 项目测点主键
     * @return 结果
     */
    public int deleteWxcxcProjectPointById(Long id);

    // 获取未分组的测点
    public List<WxcxcProjectPoint> selectWxcxcProjectPointByPointGroupIdIsNull(Long projectStructureId);
}
