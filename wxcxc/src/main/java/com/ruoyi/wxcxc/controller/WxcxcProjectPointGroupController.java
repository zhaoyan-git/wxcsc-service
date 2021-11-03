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
import com.ruoyi.wxcxc.domain.WxcxcProjectPointGroup;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测点分组Controller
 * 
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/projectPointGroup")
public class WxcxcProjectPointGroupController extends BaseController
{
    @Autowired
    private IWxcxcProjectPointGroupService wxcxProjectPointGroupService;

    /**
     * 查询测点分组列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPointGroup')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        startPage();
        List<WxcxcProjectPointGroup> list = wxcxProjectPointGroupService.selectWxcxcProjectPointGroupList(wxcxcProjectPointGroup);
        return getDataTable(list);
    }

    /**
     * 导出测点分组列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPointGroup')")
    @Log(title = "测点分组", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        List<WxcxcProjectPointGroup> list = wxcxProjectPointGroupService.selectWxcxcProjectPointGroupList(wxcxcProjectPointGroup);
        ExcelUtil<WxcxcProjectPointGroup> util = new ExcelUtil<WxcxcProjectPointGroup>(WxcxcProjectPointGroup.class);
        return util.exportExcel(list, "测点分组数据");
    }

    /**
     * 获取测点分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPointGroup')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxProjectPointGroupService.selectWxcxcProjectPointGroupById(id));
    }

    /**
     * 新增测点分组
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPointGroup')")
    @Log(title = "测点分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        return toAjax(wxcxProjectPointGroupService.insertWxcxcProjectPointGroup(wxcxcProjectPointGroup));
    }

    /**
     * 修改测点分组
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPointGroup')")
    @Log(title = "测点分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectPointGroup wxcxcProjectPointGroup)
    {
        return toAjax(wxcxProjectPointGroupService.updateWxcxcProjectPointGroup(wxcxcProjectPointGroup));
    }

    /**
     * 删除测点分组
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPointGroup')")
    @Log(title = "测点分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxProjectPointGroupService.deleteWxcxcProjectPointGroupByIds(ids));
    }
}
