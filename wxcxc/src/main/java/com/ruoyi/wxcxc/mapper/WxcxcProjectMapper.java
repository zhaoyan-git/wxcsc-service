package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProject;

/**
 * 项目Mapper接口
 * 
 * @author l62202
 * @date 2021-10-17
 */
public interface WxcxcProjectMapper 
{
    /**
     * 查询项目
     * 
     * @param id 项目主键
     * @return 项目
     */
    public WxcxcProject selectWxcxcProjectById(Long id);

    /**
     * 查询项目列表
     * 
     * @param wxcxcProject 项目
     * @return 项目集合
     */
    public List<WxcxcProject> selectWxcxcProjectList(WxcxcProject wxcxcProject);

    /**
     * 新增项目
     * 
     * @param wxcxcProject 项目
     * @return 结果
     */
    public int insertWxcxcProject(WxcxcProject wxcxcProject);

    /**
     * 修改项目
     * 
     * @param wxcxcProject 项目
     * @return 结果
     */
    public int updateWxcxcProject(WxcxcProject wxcxcProject);

    /**
     * 删除项目
     * 
     * @param id 项目主键
     * @return 结果
     */
    public int deleteWxcxcProjectById(Long id);

    /**
     * 批量删除项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectByIds(Long[] ids);

    // 根据用户ID获取项目列表
    public List<WxcxcProject> selectWxcxcProjectByUserId(Long selectWxcxcProjectByUserId);


}
