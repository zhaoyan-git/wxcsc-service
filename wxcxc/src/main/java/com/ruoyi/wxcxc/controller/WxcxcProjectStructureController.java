package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructure;
import com.ruoyi.wxcxc.service.IWxcxcProjectStructureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目结构物Controller
 *
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/projectStructure")
public class WxcxcProjectStructureController extends BaseController {
    @Autowired
    private IWxcxcProjectStructureService wxcxcProjectStructureService;

    /**
     * 查询项目结构物列表
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:projectStructure')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectStructure wxcxcProjectStructure) {
        if (null == wxcxcProjectStructure || null == wxcxcProjectStructure.getProjectId())
            return getDataTable(new ArrayList<>());
        startPage();
        List<WxcxcProjectStructure> list = wxcxcProjectStructureService.selectWxcxcProjectStructureList(wxcxcProjectStructure);
        return getDataTable(list);
    }

    /**
     * 导出项目结构物列表
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:projectStructure')")
    @Log(title = "项目结构物", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectStructure wxcxcProjectStructure) {
        List<WxcxcProjectStructure> list = wxcxcProjectStructureService.selectWxcxcProjectStructureList(wxcxcProjectStructure);
        ExcelUtil<WxcxcProjectStructure> util = new ExcelUtil<WxcxcProjectStructure>(WxcxcProjectStructure.class);
        return util.exportExcel(list, "项目结构物数据");
    }

    /**
     * 获取项目结构物详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:projectStructure')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxcxcProjectStructureService.selectWxcxcProjectStructureById(id));
    }

    /**
     * 新增项目结构物
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:projectStructure')")
    @Log(title = "项目结构物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectStructure wxcxcProjectStructure) throws Exception {
        return toAjax(wxcxcProjectStructureService.insertWxcxcProjectStructure(wxcxcProjectStructure));
    }

    /**
     * 修改项目结构物
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:projectStructure')")
    @Log(title = "项目结构物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectStructure wxcxcProjectStructure) {
        return toAjax(wxcxcProjectStructureService.updateWxcxcProjectStructure(wxcxcProjectStructure));
    }

    /**
     * 删除项目结构物
     */
    @PreAuthorize("@ss.hasPermi('iot:integrate:projectStructure')")
    @Log(title = "项目结构物", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcProjectStructureService.deleteWxcxcProjectStructureByIds(ids));
    }
}
