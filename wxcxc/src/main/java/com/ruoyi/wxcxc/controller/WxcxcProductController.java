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
import com.ruoyi.wxcxc.domain.WxcxcProduct;
import com.ruoyi.wxcxc.service.IWxcxcProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品管理Controller
 * 
 * @author l62202
 * @date 2021-10-30
 */
@RestController
@RequestMapping("/iot/product")
public class WxcxcProductController extends BaseController
{
    @Autowired
    private IWxcxcProductService wxcxcProductService;

    /**
     * 查询产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('iot:product')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProduct wxcxcProduct)
    {
        startPage();
        List<WxcxcProduct> list = wxcxcProductService.selectWxcxcProductList(wxcxcProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('iot:product')")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProduct wxcxcProduct)
    {
        List<WxcxcProduct> list = wxcxcProductService.selectWxcxcProductList(wxcxcProduct);
        ExcelUtil<WxcxcProduct> util = new ExcelUtil<WxcxcProduct>(WxcxcProduct.class);
        return util.exportExcel(list, "产品管理数据");
    }

    /**
     * 获取产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:product')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wxcxcProductService.selectWxcxcProductById(id));
    }

    /**
     * 新增产品管理
     */
    @PreAuthorize("@ss.hasPermi('iot:product')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProduct wxcxcProduct)
    {
        return toAjax(wxcxcProductService.insertWxcxcProduct(wxcxcProduct));
    }

    /**
     * 修改产品管理
     */
    @PreAuthorize("@ss.hasPermi('iot:product')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProduct wxcxcProduct)
    {
        return toAjax(wxcxcProductService.updateWxcxcProduct(wxcxcProduct));
    }

    /**
     * 删除产品管理
     */
    @PreAuthorize("@ss.hasPermi('iot:product')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxcxcProductService.deleteWxcxcProductByIds(ids));
    }
}
