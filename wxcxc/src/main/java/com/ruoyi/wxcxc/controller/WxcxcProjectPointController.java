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
import com.ruoyi.wxcxc.domain.WxcxcProjectPoint;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目测点Controller
 * 
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/projectPoint")
public class WxcxcProjectPointController extends BaseController
{
    @Autowired
    private IWxcxcProjectPointService wxcxcProjectPointService;

    /**
     * 查询项目测点列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectPoint wxcxcProjectPoint)
    {
        startPage();
        List<WxcxcProjectPoint> list = wxcxcProjectPointService.selectWxcxcProjectPointList(wxcxcProjectPoint);
        return getDataTable(list);
    }

    /**
     * 导出项目测点列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint:export')")
    @Log(title = "项目测点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectPoint wxcxcProjectPoint)
    {
        List<WxcxcProjectPoint> list = wxcxcProjectPointService.selectWxcxcProjectPointList(wxcxcProjectPoint);
        ExcelUtil<WxcxcProjectPoint> util = new ExcelUtil<WxcxcProjectPoint>(WxcxcProjectPoint.class);
        return util.exportExcel(list, "项目测点数据");
    }

    /**
     * 获取项目测点详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcProjectPointService.selectWxcxcProjectPointById(id));
    }

    /**
     * 新增项目测点
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint:add')")
    @Log(title = "项目测点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectPoint wxcxcProjectPoint)
    {
        return toAjax(wxcxcProjectPointService.insertWxcxcProjectPoint(wxcxcProjectPoint));
    }

    /**
     * 修改项目测点
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint:edit')")
    @Log(title = "项目测点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectPoint wxcxcProjectPoint)
    {
        return toAjax(wxcxcProjectPointService.updateWxcxcProjectPoint(wxcxcProjectPoint));
    }

    /**
     * 删除项目测点
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint:remove')")
    @Log(title = "项目测点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcProjectPointService.deleteWxcxcProjectPointByIds(ids));
    }
}
