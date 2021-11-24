package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.wxcxc.util.SendDataUtil;
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
import com.ruoyi.wxcxc.domain.WxcxcDeviceGateway;
import com.ruoyi.wxcxc.service.IWxcxcDeviceGatewayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备网关Controller
 *
 * @author l62202
 * @date 2021-10-31
 */
@RestController
@RequestMapping("/iot/deviceGateway")
public class WxcxcDeviceGatewayController extends BaseController {
    @Autowired
    private IWxcxcDeviceGatewayService wxcxcDeviceGatewayService;

    /**
     * 查询设备网关列表
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcDeviceGateway wxcxcDeviceGateway) {
        if (null == wxcxcDeviceGateway || null == wxcxcDeviceGateway.getProjectId())
            return getDataTable(new ArrayList<>());

        startPage();
        List<WxcxcDeviceGateway> list = wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayList(wxcxcDeviceGateway);
        return getDataTable(list);
    }

    /**
     * 导出设备网关列表
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @Log(title = "设备网关", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcDeviceGateway wxcxcDeviceGateway) {
        List<WxcxcDeviceGateway> list = wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayList(wxcxcDeviceGateway);
        ExcelUtil<WxcxcDeviceGateway> util = new ExcelUtil<WxcxcDeviceGateway>(WxcxcDeviceGateway.class);
        return util.exportExcel(list, "设备网关数据");
    }

    /**
     * 获取设备网关详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxcxcDeviceGatewayService.selectWxcxcDeviceGatewayById(id));
    }

    /**
     * 新增设备网关
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @Log(title = "设备网关", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcDeviceGateway wxcxcDeviceGateway) {
        return toAjax(wxcxcDeviceGatewayService.insertWxcxcDeviceGateway(wxcxcDeviceGateway));
    }

    /**
     * 修改设备网关
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @Log(title = "设备网关", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcDeviceGateway wxcxcDeviceGateway) {
        return toAjax(wxcxcDeviceGatewayService.updateWxcxcDeviceGateway(wxcxcDeviceGateway));
    }

    /**
     * 删除设备网关
     */
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @Log(title = "设备网关", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcDeviceGatewayService.deleteWxcxcDeviceGatewayByIds(ids));
    }

    // 手动采集
    @PreAuthorize("@ss.hasPermi('iot:deviceGateway')")
    @GetMapping("/manualGet")
    public void manualGet(WxcxcDeviceGateway wxcxcDeviceGateway) {
        SendDataUtil.sendRequest(wxcxcDeviceGateway.getId());
    }

}
