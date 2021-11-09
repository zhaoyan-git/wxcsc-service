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
import com.ruoyi.wxcxc.domain.WxcxcDataBusiness;
import com.ruoyi.wxcxc.service.IWxcxcDataBusinessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业数据Controller
 * 
 * @author l62202
 * @date 2021-11-03
 */
@RestController
@RequestMapping("/iot/businessData")
public class WxcxcDataBusinessController extends BaseController
{
    @Autowired
    private IWxcxcDataBusinessService wxcxcDataBusinessService;

    /**
     * 查询企业数据列表
     */
    @PreAuthorize("@ss.hasPermi('iot:businessData:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcDataBusiness wxcxcDataBusiness)
    {
        startPage();
        List<WxcxcDataBusiness> list = wxcxcDataBusinessService.selectWxcxcDataBusinessList(wxcxcDataBusiness);
        return getDataTable(list);
    }

    /**
     * 导出企业数据列表
     */
    @PreAuthorize("@ss.hasPermi('iot:businessData:export')")
    @Log(title = "企业数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcDataBusiness wxcxcDataBusiness)
    {
        List<WxcxcDataBusiness> list = wxcxcDataBusinessService.selectWxcxcDataBusinessList(wxcxcDataBusiness);
        ExcelUtil<WxcxcDataBusiness> util = new ExcelUtil<WxcxcDataBusiness>(WxcxcDataBusiness.class);
        return util.exportExcel(list, "企业数据数据");
    }

    /**
     * 获取企业数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:businessData:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcDataBusinessService.selectWxcxcDataBusinessById(id));
    }

    /**
     * 新增企业数据
     */
    @PreAuthorize("@ss.hasPermi('iot:businessData:add')")
    @Log(title = "企业数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcDataBusiness wxcxcDataBusiness)
    {
        return toAjax(wxcxcDataBusinessService.insertWxcxcDataBusiness(wxcxcDataBusiness));
    }

    /**
     * 修改企业数据
     */
    @PreAuthorize("@ss.hasPermi('iot:businessData:edit')")
    @Log(title = "企业数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcDataBusiness wxcxcDataBusiness)
    {
        return toAjax(wxcxcDataBusinessService.updateWxcxcDataBusiness(wxcxcDataBusiness));
    }

    /**
     * 删除企业数据
     */
    @PreAuthorize("@ss.hasPermi('iot:businessData:remove')")
    @Log(title = "企业数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcDataBusinessService.deleteWxcxcDataBusinessByIds(ids));
    }
}
