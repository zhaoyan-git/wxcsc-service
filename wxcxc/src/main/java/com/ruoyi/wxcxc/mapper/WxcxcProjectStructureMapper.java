package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructure;

/**
 * 项目结构物Mapper接口
 * 
 * @author l62202
 * @date 2021-10-17
 */
public interface WxcxcProjectStructureMapper 
{
    /**
     * 查询项目结构物
     * 
     * @param id 项目结构物主键
     * @return 项目结构物
     */
    public WxcxcProjectStructure selectWxcxcProjectStructureById(Long id);

    /**
     * 查询项目结构物列表
     * 
     * @param wxcxcProjectStructure 项目结构物
     * @return 项目结构物集合
     */
    public List<WxcxcProjectStructure> selectWxcxcProjectStructureList(WxcxcProjectStructure wxcxcProjectStructure);

    /**
     * 新增项目结构物
     * 
     * @param wxcxcProjectStructure 项目结构物
     * @return 结果
     */
    public int insertWxcxcProjectStructure(WxcxcProjectStructure wxcxcProjectStructure);

    /**
     * 修改项目结构物
     * 
     * @param wxcxcProjectStructure 项目结构物
     * @return 结果
     */
    public int updateWxcxcProjectStructure(WxcxcProjectStructure wxcxcProjectStructure);

    /**
     * 删除项目结构物
     * 
     * @param id 项目结构物主键
     * @return 结果
     */
    public int deleteWxcxcProjectStructureById(Long id);

    /**
     * 批量删除项目结构物
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProjectStructureByIds(Long[] ids);
}
