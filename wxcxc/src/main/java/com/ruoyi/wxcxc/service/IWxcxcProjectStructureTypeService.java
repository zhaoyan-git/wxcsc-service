package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructureType;

/**
 * 结构物选项Service接口
 * 
 * @author l62202
 * @date 2021-10-16
 */
public interface IWxcxcProjectStructureTypeService
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
     * 批量删除结构物选项
     * 
     * @param ids 需要删除的结构物选项主键集合
     * @return 结果
     */
    public int deleteWxcxProjectStructureTypeByIds(Long[] ids);

    /**
     * 删除结构物选项信息
     * 
     * @param id 结构物选项主键
     * @return 结果
     */
    public int deleteWxcxProjectStructureTypeById(Long id);
}
