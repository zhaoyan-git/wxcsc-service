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
import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.service.IWxcxcDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备表Controller
 * 
 * @author l62202
 * @date 2021-10-31
 */
@RestController
@RequestMapping("/iot/device")
public class WxcxcDeviceController extends BaseController
{
    @Autowired
    private IWxcxcDeviceService wxcxcDeviceService;

    /**
     * 查询设备表列表
     */
    @PreAuthorize("@ss.hasPermi('iot:device')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcDevice wxcxcDevice)
    {
        if (null == wxcxcDevice || null == wxcxcDevice.getProjectId())
            return getDataTable(new ArrayList<>());

        startPage();
        List<WxcxcDevice> list = wxcxcDeviceService.selectWxcxcDeviceList(wxcxcDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备表列表
     */
    @PreAuthorize("@ss.hasPermi('iot:device')")
    @Log(title = "设备表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcDevice wxcxcDevice)
    {
        List<WxcxcDevice> list = wxcxcDeviceService.selectWxcxcDeviceList(wxcxcDevice);
        ExcelUtil<WxcxcDevice> util = new ExcelUtil<WxcxcDevice>(WxcxcDevice.class);
        return util.exportExcel(list, "设备表数据");
    }

    /**
     * 获取设备表详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:device')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcDeviceService.selectWxcxcDeviceById(id));
    }

    /**
     * 新增设备表
     */
    @PreAuthorize("@ss.hasPermi('iot:device')")
    @Log(title = "设备表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcDevice wxcxcDevice)
    {
        return toAjax(wxcxcDeviceService.insertWxcxcDevice(wxcxcDevice));
    }

    /**
     * 修改设备表
     */
    @PreAuthorize("@ss.hasPermi('iot:device')")
    @Log(title = "设备表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcDevice wxcxcDevice)
    {
        return toAjax(wxcxcDeviceService.updateWxcxcDevice(wxcxcDevice));
    }

    /**
     * 删除设备表
     */
    @PreAuthorize("@ss.hasPermi('iot:device')")
    @Log(title = "设备表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcDeviceService.deleteWxcxcDeviceByIds(ids));
    }
}
