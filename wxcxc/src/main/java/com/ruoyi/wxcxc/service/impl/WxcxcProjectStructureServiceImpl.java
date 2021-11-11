package com.ruoyi.wxcxc.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxcxc.mapper.WxcxcProjectStructureMapper;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructure;
import com.ruoyi.wxcxc.service.IWxcxcProjectStructureService;

/**
 * 项目结构物Service业务层处理
 *
 * @author l62202
 * @date 2021-10-17
 */
@Service
public class WxcxcProjectStructureServiceImpl implements IWxcxcProjectStructureService {
    @Autowired
    private WxcxcProjectStructureMapper wxcxcProjectStructureMapper;
    @Autowired
    private IWxcxcProjectPointDataService wxcxcProjectPointDataService;

    /**
     * 查询项目结构物
     *
     * @param id 项目结构物主键
     * @return 项目结构物
     */
    @Override
    public WxcxcProjectStructure selectWxcxcProjectStructureById(Long id) {
        return wxcxcProjectStructureMapper.selectWxcxcProjectStructureById(id);
    }

    /**
     * 查询项目结构物列表
     *
     * @param wxcxcProjectStructure 项目结构物
     * @return 项目结构物
     */
    @Override
    public List<WxcxcProjectStructure> selectWxcxcProjectStructureList(WxcxcProjectStructure wxcxcProjectStructure) {
        return wxcxcProjectStructureMapper.selectWxcxcProjectStructureList(wxcxcProjectStructure);
    }

    /**
     * 新增项目结构物
     *
     * @param wxcxcProjectStructure 项目结构物
     * @return 结果
     */
    @Override
    public int insertWxcxcProjectStructure(WxcxcProjectStructure wxcxcProjectStructure) throws Exception {
        wxcxcProjectStructure.setCreateTime(DateUtils.getNowDate());
        int res = wxcxcProjectStructureMapper.insertWxcxcProjectStructure(wxcxcProjectStructure);
        // 创建测点数据表
        wxcxcProjectPointDataService.createTable(wxcxcProjectStructure.getId());

        return res;
    }

    /**
     * 修改项目结构物
     *
     * @param wxcxcProjectStructure 项目结构物
     * @return 结果
     */
    @Override
    public int updateWxcxcProjectStructure(WxcxcProjectStructure wxcxcProjectStructure) {
        wxcxcProjectStructure.setUpdateTime(DateUtils.getNowDate());
        return wxcxcProjectStructureMapper.updateWxcxcProjectStructure(wxcxcProjectStructure);
    }

    /**
     * 批量删除项目结构物
     *
     * @param ids 需要删除的项目结构物主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectStructureByIds(Long[] ids) {
        return wxcxcProjectStructureMapper.deleteWxcxcProjectStructureByIds(ids);
    }

    /**
     * 删除项目结构物信息
     *
     * @param id 项目结构物主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProjectStructureById(Long id) {
        return wxcxcProjectStructureMapper.deleteWxcxcProjectStructureById(id);
    }
}
