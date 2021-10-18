package com.ruoyi.wxcxc.controller;

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
import com.ruoyi.wxcxc.domain.WxcxcProject;
import com.ruoyi.wxcxc.service.IWxcxcProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目Controller
 * 
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/project")
public class WxcxcProjectController extends BaseController
{
    @Autowired
    private IWxcxcProjectService wxcxcProjectService;

    /**
     * 查询项目列表
     */
    @PreAuthorize("@ss.hasPermi('iot:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProject wxcxcProject)
    {
        startPage();
        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        return getDataTable(list);
    }

    /**
     * 导出项目列表
     */
    @PreAuthorize("@ss.hasPermi('iot:project:export')")
    @Log(title = "项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProject wxcxcProject)
    {
        List<WxcxcProject> list = wxcxcProjectService.selectWxcxcProjectList(wxcxcProject);
        ExcelUtil<WxcxcProject> util = new ExcelUtil<WxcxcProject>(WxcxcProject.class);
        return util.exportExcel(list, "项目数据");
    }

    /**
     * 获取项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcProjectService.selectWxcxcProjectById(id));
    }

    /**
     * 新增项目
     */
    @PreAuthorize("@ss.hasPermi('iot:project:add')")
    @Log(title = "项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProject wxcxcProject)
    {
        return toAjax(wxcxcProjectService.insertWxcxcProject(wxcxcProject));
    }

    /**
     * 修改项目
     */
    @PreAuthorize("@ss.hasPermi('iot:project:edit')")
    @Log(title = "项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProject wxcxcProject)
    {
        return toAjax(wxcxcProjectService.updateWxcxcProject(wxcxcProject));
    }

    /**
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('iot:project:remove')")
    @Log(title = "项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcProjectService.deleteWxcxcProjectByIds(ids));
    }
}
