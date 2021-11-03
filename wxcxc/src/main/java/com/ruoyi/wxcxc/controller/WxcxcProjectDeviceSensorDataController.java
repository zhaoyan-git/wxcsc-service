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
import com.ruoyi.wxcxc.domain.WxcxcProjectDeviceSensorData;
import com.ruoyi.wxcxc.service.IWxcxcProjectDeviceSensorDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目设备数据表Controller
 * 
 * @author l62202
 * @date 2021-11-02
 */
@RestController
@RequestMapping("/iot/projectDeivceSensorData")
public class WxcxcProjectDeviceSensorDataController extends BaseController
{
    @Autowired
    private IWxcxcProjectDeviceSensorDataService wxcxcProjectDeviceSensorDataService;

//    /**
//     * 查询项目设备数据表列表
//     */
//    @PreAuthorize("@ss.hasPermi('iot:projectDeivceSensorData:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        startPage();
//        List<WxcxcProjectDeviceSensorData> list = wxcxcProjectDeviceSensorDataService.selectWxcxcProjectDeviceSensorDataList(wxcxcProjectDeviceSensorData);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出项目设备数据表列表
//     */
//    @PreAuthorize("@ss.hasPermi('iot:projectDeivceSensorData:export')")
//    @Log(title = "项目设备数据表", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        List<WxcxcProjectDeviceSensorData> list = wxcxcProjectDeviceSensorDataService.selectWxcxcProjectDeviceSensorDataList(wxcxcProjectDeviceSensorData);
//        ExcelUtil<WxcxcProjectDeviceSensorData> util = new ExcelUtil<WxcxcProjectDeviceSensorData>(WxcxcProjectDeviceSensorData.class);
//        return util.exportExcel(list, "项目设备数据表数据");
//    }
//
//    /**
//     * 获取项目设备数据表详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('iot:projectDeivceSensorData:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return AjaxResult.success(wxcxcProjectDeviceSensorDataService.selectWxcxcProjectDeviceSensorDataById(id));
//    }
//
//    /**
//     * 新增项目设备数据表
//     */
//    @PreAuthorize("@ss.hasPermi('iot:projectDeivceSensorData:add')")
//    @Log(title = "项目设备数据表", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        return toAjax(wxcxcProjectDeviceSensorDataService.insertWxcxcProjectDeviceSensorData(wxcxcProjectDeviceSensorData));
//    }
//
//    /**
//     * 修改项目设备数据表
//     */
//    @PreAuthorize("@ss.hasPermi('iot:projectDeivceSensorData:edit')")
//    @Log(title = "项目设备数据表", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorData)
//    {
//        return toAjax(wxcxcProjectDeviceSensorDataService.updateWxcxcProjectDeviceSensorData(wxcxcProjectDeviceSensorData));
//    }
//
//    /**
//     * 删除项目设备数据表
//     */
//    @PreAuthorize("@ss.hasPermi('iot:projectDeivceSensorData:remove')")
//    @Log(title = "项目设备数据表", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(wxcxcProjectDeviceSensorDataService.deleteWxcxcProjectDeviceSensorDataByIds(ids));
//    }
}
