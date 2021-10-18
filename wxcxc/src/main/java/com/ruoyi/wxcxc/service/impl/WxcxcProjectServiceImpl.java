package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectMapper;
import com.ruoyi.wxcxc.domain.WxcxcProject;
import com.ruoyi.wxcxc.service.IWxcxcProjectService;

/**
 * 项目Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcProjectServiceImpl implements IWxcxcProjectService 
{
    @Autowired
    private WxcxcProjectMapper wxcxcProjectMapper;

    /**
     * 查询项目
     * 
     * @param id 项目主键
     * @return 项目
     */
    @Override
    public WxcxcProject selectWxcxcProjectById(Long id)
    {
        return wxcxcProjectMapper.selectWxcxcProjectById(id);
    }

    /**
     * 查询项目列表
     * 
     * @param wxcxcProject 项目
     * @return 项目
     */
    @Override
    public List<WxcxcProject> selectWxcxcProjectList(WxcxcProject wxcxcProject)
    {
        return wxcxcProjectMapper.selectWxcxcProjectList(wxcxcProject);
    }

    /**
     * 新增项目
     * 
     * @param wxcxcProject 项目
     * @return 结果
     */
    @Override
    public int insertWxcxcProject(WxcxcProject wxcxcProject)
    {
        wxcxcProject.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectMapper.insertWxcxcProject(wxcxcProject);
    }

    /**
     * 修改项目
     * 
     * @param wxcxcProject 项目
     * @return 结果
     */
    @Override
    public int updateWxcxcProject(WxcxcProject wxcxcProject)
    {
        wxcxcProject.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectMapper.updateWxcxcProject(wxcxcProject);
    }

    /**
     * 批量删除项目
     * 
     * @param ids 需要删除的项目主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectByIds(Long[] ids)
    {
        return wxcxcProjectMapper.deleteWxcxcProjectByIds(ids);
    }

    /**
     * 删除项目信息
     * 
     * @param id 项目主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectById(Long id)
    {
        return wxcxcProjectMapper.deleteWxcxcProjectById(id);
    }
}
