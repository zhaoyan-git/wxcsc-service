package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructureType;

/**
 * 结构物选项Mapper接口
 * 
 * @author l62202
 * @date 2021-10-16
 */
public interface WxcxcProjectStructureTypeMapper
{
    /**
     * 查询结构物选项
     * 
     * @param id 结构物选项主键
     * @return 结构物选项
     */
    public WxcxcProjectStructureType selectWxcxProjectStructureTypeById(Long id);

    /**
     * 查询结构物选项列表
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结构物选项集合
     */
    public List<WxcxcProjectStructureType> selectWxcxProjectStructureTypeList(WxcxcProjectStructureType wxcxcProjectStructureType);

    /**
     * 新增结构物选项
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结果
     */
    public int insertWxcxProjectStructureType(WxcxcProjectStructureType wxcxcProjectStructureType);

    /**
     * 修改结构物选项
     * 
     * @param wxcxcProjectStructureType 结构物选项
     * @return 结果
     */
    public int updateWxcxProjectStructureType(WxcxcProjectStructureType wxcxcProjectStructureType);

    /**
     * 删除结构物选项
     * 
     * @param id 结构物选项主键
     * @return 结果
     */
    public int deleteWxcxProjectStructureTypeById(Long id);

    /**
     * 批量删除结构物选项
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxProjectStructureTypeByIds(Long[] ids);
}
