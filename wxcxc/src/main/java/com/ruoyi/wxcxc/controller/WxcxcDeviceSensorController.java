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
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.service.IWxcxcDeviceSensorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备传感器Controller
 * 
 * @author l62202
 * @date 2021-10-31
 */
@RestController
@RequestMapping("/iot/deviceSensor")
public class WxcxcDeviceSensorController extends BaseController
{
    @Autowired
    private IWxcxcDeviceSensorService wxcxcDeviceSensorService;

    /**
     * 查询设备传感器列表
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceSensor:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        startPage();
        List<WxcxcDeviceSensor> list = wxcxcDeviceSensorService.selectWxcxcDeviceSensorList(wxcxcDeviceSensor);
        return getDataTable(list);
    }

    /**
     * 导出设备传感器列表
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceSensor:export')")
    @Log(title = "设备传感器", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        List<WxcxcDeviceSensor> list = wxcxcDeviceSensorService.selectWxcxcDeviceSensorList(wxcxcDeviceSensor);
        ExcelUtil<WxcxcDeviceSensor> util = new ExcelUtil<WxcxcDeviceSensor>(WxcxcDeviceSensor.class);
        return util.exportExcel(list, "设备传感器数据");
    }

    /**
     * 获取设备传感器详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceSensor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcDeviceSensorService.selectWxcxcDeviceSensorById(id));
    }

    /**
     * 新增设备传感器
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceSensor:add')")
    @Log(title = "设备传感器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        return toAjax(wxcxcDeviceSensorService.insertWxcxcDeviceSensor(wxcxcDeviceSensor));
    }

    /**
     * 修改设备传感器
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceSensor:edit')")
    @Log(title = "设备传感器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcDeviceSensor wxcxcDeviceSensor)
    {
        return toAjax(wxcxcDeviceSensorService.updateWxcxcDeviceSensor(wxcxcDeviceSensor));
    }

    /**
     * 删除设备传感器
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceSensor:remove')")
    @Log(title = "设备传感器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcDeviceSensorService.deleteWxcxcDeviceSensorByIds(ids));
    }
}
