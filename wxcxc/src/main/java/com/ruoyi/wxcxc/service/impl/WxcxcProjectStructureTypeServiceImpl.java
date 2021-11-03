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
    public WxcxcProjectStructureType selectWxcxcProjectStructureTypeById(Long id)
    {
        return wxcxcProjectStructureTypeMapper.selectWxcxcProjectStructureTypeById(id);
    }

    /**
     * 查询结构物选项列表
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结构物选项
     */
    @Override
    public List<WxcxcProjectStructureType> selectWxcxcProjectStructureTypeList(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        return wxcxcProjectStructureTypeMapper.selectWxcxcProjectStructureTypeList(wxcxcProjectStructureType);
    }

    /**
     * 新增结构物选项
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectStructureType(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        wxcxcProjectStructureType.setCreateTime(DateUtils.getNowDate());
        return wxcxcProjectStructureTypeMapper.insertWxcxcProjectStructureType(wxcxcProjectStructureType);
    }

    /**
     * 修改结构物选项
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectStructureType(WxcxcProjectStructureType wxcxcProjectStructureType)
    {
        wxcxcProjectStructureType.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectStructureTypeMapper.updateWxcxcProjectStructureType(wxcxcProjectStructureType);
    }

    /**
     * 批量删除结构物选项
     * 
     * @param ids 需要删除的结构物选项主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectStructureTypeByIds(Long[] ids)
    {
        return wxcxcProjectStructureTypeMapper.deleteWxcxcProjectStructureTypeByIds(ids);
    }

    /**
     * 删除结构物选项信息
     * 
     * @param id 结构物选项主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectStructureTypeById(Long id)
    {
        return wxcxcProjectStructureTypeMapper.deleteWxcxcProjectStructureTypeById(id);
    }
}
