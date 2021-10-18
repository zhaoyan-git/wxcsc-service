package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectStructureTypeMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructureType;
import com.ruoyi.wxcxc.service.IWxcxcProjectStructureTypeService;

/**
 * 结构物选项Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-16
 */
@Service
public class WxcxcProjectStructureTypeServiceImpl implements IWxcxcProjectStructureTypeService
{
    @Autowired
    private WxcxcProjectStructureTypeMapper wxcxcProjectStructureTypeMapper;

    /**
     * 查询结构物选项
     * 
     * @param id 结构物选项主键
     * @return 结构物选项
     */
    @Override
    public WxcxcProjectStructureType selectWxcxProjectStructureTypeById(Long id)
    {
        return wxcxcProjectStructureTypeMapper.selectWxcxProjectStructureTypeById(id);
    }

    /**
     * 查询结构物选项列表
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结构物选项
     */
    @Override
    public List<WxcxcProjectStructureType> selectWxcxProjectStructureTypeList(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        return wxcxcProjectStructureTypeMapper.selectWxcxProjectStructureTypeList(wxcxcProjectStructureType);
    }

    /**
     * 新增结构物选项
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结果
     */
    @Override
    public int insertWxcxProjectStructureType(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        wxcxcProjectStructureType.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectStructureTypeMapper.insertWxcxProjectStructureType(wxcxcProjectStructureType);
    }

    /**
     * 修改结构物选项
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结果
     */
    @Override
    public int updateWxcxProjectStructureType(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        wxcxcProjectStructureType.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectStructureTypeMapper.updateWxcxProjectStructureType(wxcxcProjectStructureType);
    }

    /**
     * 批量删除结构物选项
     * 
     * @param ids 需要删除的结构物选项主键
     * @return 结果
     */
    @Override
    public int deleteWxcxProjectStructureTypeByIds(Long[] ids)
    {
        return wxcxcProjectStructureTypeMapper.deleteWxcxProjectStructureTypeByIds(ids);
    }

    /**
     * 删除结构物选项信息
     * 
     * @param id 结构物选项主键
     * @return 结果
     */
    @Override
    public int deleteWxcxProjectStructureTypeById(Long id)
    {
        return wxcxcProjectStructureTypeMapper.deleteWxcxProjectStructureTypeById(id);
    }
}
