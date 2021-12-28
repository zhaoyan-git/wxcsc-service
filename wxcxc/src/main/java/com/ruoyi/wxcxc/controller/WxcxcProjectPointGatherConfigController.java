package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectPoint;
import com.ruoyi.wxcxc.domain.WxcxcProjectStructure;
import com.ruoyi.wxcxc.dto.WxcxcProjectPointGatherConfigDto;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointMapper;
import com.ruoyi.wxcxc.mapper.WxcxcProjectStructureMapper;
import com.ruoyi.wxcxc.util.PackDto;
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
import com.ruoyi.wxcxc.domain.WxcxcProjectPointGatherConfig;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointGatherConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 聚集配置Controller
 *
 * @author l62202
 * @date 2021-12-06
 */
@RestController
@RequestMapping("/iot/pointGatherConfig")
public class WxcxcProjectPointGatherConfigController extends BaseController {
    @Autowired
    private IWxcxcProjectPointGatherConfigService wxcxcProjectPointGatherConfigService;
    @Autowired
    private WxcxcProjectPointMapper wxcxcProjectPointMapper;
    @Autowired
    private WxcxcProjectStructureMapper wxcxcProjectStructureMapper;
    @Autowired
    private PackDto packDto;

    /**
     * 查询聚集配置列表
     */
    @PreAuthorize("@ss.hasPermi('iot:pointGatherConfig')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfigCondition) {
        if (null == wxcxcProjectPointGatherConfigCondition || null == wxcxcProjectPointGatherConfigCondition.getProjectId())
            return getDataTable(new ArrayList<>());

        startPage();
        List<WxcxcProjectPointGatherConfig> list = wxcxcProjectPointGatherConfigService.selectWxcxcProjectPointGatherConfigList(wxcxcProjectPointGatherConfigCondition);
        List<WxcxcProjectPointGatherConfigDto> dtoList = new ArrayList<>();

        for (WxcxcProjectPointGatherConfig item : list) {
            WxcxcProjectPoint wxcxcProjectPoint = wxcxcProjectPointMapper.selectWxcxcProjectPointById(item.getPointId());
            if (null == wxcxcProjectPoint) continue;
            WxcxcProjectStructure wxcxcProjectStructure = wxcxcProjectStructureMapper.selectWxcxcProjectStructureById(wxcxcProjectPoint.getProjectStructureId());
            if (null == wxcxcProjectStructure) continue;
            WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfigDto = new WxcxcProjectPointGatherConfigDto();

            wxcxcProjectPointGatherConfigDto.setCalcType(item.getCalcType());
            wxcxcProjectPointGatherConfigDto.setId(item.getId());
            wxcxcProjectPointGatherConfigDto.setCycle(item.getCycle());
            wxcxcProjectPointGatherConfigDto.setPointId(item.getPointId());
            wxcxcProjectPointGatherConfigDto.setProjectId(wxcxcProjectStructure.getProjectId());
            wxcxcProjectPointGatherConfigDto.setStructureId(wxcxcProjectPoint.getProjectStructureId());

            dtoList.add(wxcxcProjectPointGatherConfigDto);
        }

        return getDataTable(dtoList);
    }

    /**
     * 导出聚集配置列表
     */
    @PreAuthorize("@ss.hasPermi('iot:pointGatherConfig')")
    @Log(title = "聚集配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfig) {
        List<WxcxcProjectPointGatherConfig> list = wxcxcProjectPointGatherConfigService.selectWxcxcProjectPointGatherConfigList(wxcxcProjectPointGatherConfig);
        ExcelUtil<WxcxcProjectPointGatherConfig> util = new ExcelUtil<WxcxcProjectPointGatherConfig>(WxcxcProjectPointGatherConfig.class);
        return util.exportExcel(list, "聚集配置数据");
    }

    /**
     * 获取聚集配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:pointGatherConfig')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(packDto.generateWxcxcProjectPointGatherConfigDtoById(id));
    }

    /**
     * 新增聚集配置
     */
    @PreAuthorize("@ss.hasPermi('iot:pointGatherConfig')")
    @Log(title = "聚集配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectPointGatherConfig wxcxcProjectPointGatherConfig) {
        return toAjax(wxcxcProjectPointGatherConfigService.insertWxcxcProjectPointGatherConfig(wxcxcProjectPointGatherConfig));
    }

    /**
     * 修改聚集配置
     */
    @PreAuthorize("@ss.hasPermi('iot:pointGatherConfig')")
    @Log(title = "聚集配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectPointGatherConfig wxcxcProjectPointGatherConfig) {
        return toAjax(wxcxcProjectPointGatherConfigService.updateWxcxcProjectPointGatherConfig(wxcxcProjectPointGatherConfig));
    }

    /**
     * 删除聚集配置
     */
    @PreAuthorize("@ss.hasPermi('iot:pointGatherConfig')")
    @Log(title = "聚集配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcProjectPointGatherConfigService.deleteWxcxcProjectPointGatherConfigByIds(ids));
    }
}
