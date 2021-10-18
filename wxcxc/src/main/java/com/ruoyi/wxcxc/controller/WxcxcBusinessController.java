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
import com.ruoyi.wxcxc.domain.WxcxcBusiness;
import com.ruoyi.wxcxc.service.IWxcxcBusinessService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业信息Controller
 * 
 * @author l62202
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/iot/business")
public class WxcxcBusinessController extends BaseController
{
    @Autowired
    private IWxcxcBusinessService wxcxcBusinessService;

    /**
     * 查询企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('iot:business:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcBusiness wxcxcBusiness)
    {
        startPage();
        List<WxcxcBusiness> list = wxcxcBusinessService.selectWxcxcBusinessList(wxcxcBusiness);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('iot:business:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcBusiness wxcxcBusiness)
    {
        List<WxcxcBusiness> list = wxcxcBusinessService.selectWxcxcBusinessList(wxcxcBusiness);
        ExcelUtil<WxcxcBusiness> util = new ExcelUtil<WxcxcBusiness>(WxcxcBusiness.class);
        return util.exportExcel(list, "企业信息数据");
    }

    /**
     * 获取企业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:business:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcBusinessService.selectWxcxcBusinessById(id));
    }

    /**
     * 新增企业信息
     */
    @PreAuthorize("@ss.hasPermi('iot:business:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcBusiness wxcxcBusiness)
    {
        return toAjax(wxcxcBusinessService.insertWxcxcBusiness(wxcxcBusiness));
    }

    /**
     * 修改企业信息
     */
    @PreAuthorize("@ss.hasPermi('iot:business:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcBusiness wxcxcBusiness)
    {
        return toAjax(wxcxcBusinessService.updateWxcxcBusiness(wxcxcBusiness));
    }

    /**
     * 删除企业信息
     */
    @PreAuthorize("@ss.hasPermi('iot:business:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcBusinessService.deleteWxcxcBusinessByIds(ids));
    }
}
