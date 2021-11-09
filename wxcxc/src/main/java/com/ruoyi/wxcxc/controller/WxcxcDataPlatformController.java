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
import com.ruoyi.wxcxc.domain.WxcxcDataPlatform;
import com.ruoyi.wxcxc.service.IWxcxcDataPlatformService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 平台数据Controller
 * 
 * @author l62202
 * @date 2021-11-03
 */
@RestController
@RequestMapping("/iot/platformData")
public class WxcxcDataPlatformController extends BaseController
{
    @Autowired
    private IWxcxcDataPlatformService wxcxcDataPlatformService;

    /**
     * 查询平台数据列表
     */
    @PreAuthorize("@ss.hasPermi('iot:platformData:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcDataPlatform wxcxcDataPlatform)
    {
        startPage();
        List<WxcxcDataPlatform> list = wxcxcDataPlatformService.selectWxcxcDataPlatformList(wxcxcDataPlatform);
        return getDataTable(list);
    }

    /**
     * 导出平台数据列表
     */
    @PreAuthorize("@ss.hasPermi('iot:platformData:export')")
    @Log(title = "平台数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcDataPlatform wxcxcDataPlatform)
    {
        List<WxcxcDataPlatform> list = wxcxcDataPlatformService.selectWxcxcDataPlatformList(wxcxcDataPlatform);
        ExcelUtil<WxcxcDataPlatform> util = new ExcelUtil<WxcxcDataPlatform>(WxcxcDataPlatform.class);
        return util.exportExcel(list, "平台数据数据");
    }

    /**
     * 获取平台数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:platformData:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcDataPlatformService.selectWxcxcDataPlatformById(id));
    }

    /**
     * 新增平台数据
     */
    @PreAuthorize("@ss.hasPermi('iot:platformData:add')")
    @Log(title = "平台数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcDataPlatform wxcxcDataPlatform)
    {
        return toAjax(wxcxcDataPlatformService.insertWxcxcDataPlatform(wxcxcDataPlatform));
    }

    /**
     * 修改平台数据
     */
    @PreAuthorize("@ss.hasPermi('iot:platformData:edit')")
    @Log(title = "平台数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcDataPlatform wxcxcDataPlatform)
    {
        return toAjax(wxcxcDataPlatformService.updateWxcxcDataPlatform(wxcxcDataPlatform));
    }

    /**
     * 删除平台数据
     */
    @PreAuthorize("@ss.hasPermi('iot:platformData:remove')")
    @Log(title = "平台数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcDataPlatformService.deleteWxcxcDataPlatformByIds(ids));
    }
}
