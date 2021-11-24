package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.wxcxc.dto.WxcxcProjectAlarmConfigDto;
import com.ruoyi.wxcxc.mapper.WxcxcProjectAlarmConfigMapper;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointService;
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
import com.ruoyi.wxcxc.domain.WxcxcProjectAlarmConfig;
import com.ruoyi.wxcxc.service.IWxcxcProjectAlarmConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报警配置Controller
 *
 * @author l62202
 * @date 2021-11-11
 */
@RestController
@RequestMapping("/iot/projectAlarmConfig")
public class WxcxcProjectAlarmConfigController extends BaseController {
    @Autowired
    private IWxcxcProjectAlarmConfigService wxcxcProjectAlarmConfigService;
    @Autowired
    private IWxcxcProjectPointService wxcxcProjectPointService;
    @Autowired
    private PackDto packDto;
    @Autowired
    private WxcxcProjectAlarmConfigMapper wxcxcProjectAlarmConfigMapper;

    /**
     * 查询报警配置列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmConfig')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectAlarmConfigDto wxcxcProjectAlarmConfigDtoCondition) {
        if (null == wxcxcProjectAlarmConfigDtoCondition || null == wxcxcProjectAlarmConfigDtoCondition.getProjectId())
            return getDataTable(new ArrayList<>());

        startPage();
        List<WxcxcProjectAlarmConfig> list = wxcxcProjectAlarmConfigMapper.selectWxcxcProjectAlarmConfigListByDto(wxcxcProjectAlarmConfigDtoCondition);

        List<WxcxcProjectAlarmConfigDto> resList = new ArrayList<>();
        for (WxcxcProjectAlarmConfig item : list) {
            WxcxcProjectAlarmConfigDto wxcxcProjectAlarmConfigDto = new WxcxcProjectAlarmConfigDto();

            wxcxcProjectAlarmConfigDto.setId(item.getId());
            wxcxcProjectAlarmConfigDto.setPointId(item.getPointId());
            wxcxcProjectAlarmConfigDto.setAlarmLevel(item.getAlarmLevel());
            wxcxcProjectAlarmConfigDto.setThreshold(item.getThreshold());
            wxcxcProjectAlarmConfigDto.setComputeType(item.getComputeType());
            wxcxcProjectAlarmConfigDto.setTitle(item.getTitle());
            wxcxcProjectAlarmConfigDto.setContent(item.getContent());
            wxcxcProjectAlarmConfigDto.setPoint(wxcxcProjectPointService.selectWxcxcProjectPointById(item.getPointId()));

            resList.add(wxcxcProjectAlarmConfigDto);
        }

        return getDataTable(resList);
    }

    /**
     * 导出报警配置列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmConfig')")
    @Log(title = "报警配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig) {
        List<WxcxcProjectAlarmConfig> list = wxcxcProjectAlarmConfigService.selectWxcxcProjectAlarmConfigList(wxcxcProjectAlarmConfig);
        ExcelUtil<WxcxcProjectAlarmConfig> util = new ExcelUtil<WxcxcProjectAlarmConfig>(WxcxcProjectAlarmConfig.class);
        return util.exportExcel(list, "报警配置数据");
    }

    /**
     * 获取报警配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmConfig')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(packDto.generateWxcxcProjectAlarmConfigDtoById(id));
    }

    /**
     * 新增报警配置
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmConfig')")
    @Log(title = "报警配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig) {
        return toAjax(wxcxcProjectAlarmConfigService.insertWxcxcProjectAlarmConfig(wxcxcProjectAlarmConfig));
    }

    /**
     * 修改报警配置
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmConfig')")
    @Log(title = "报警配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectAlarmConfig wxcxcProjectAlarmConfig) {
        return toAjax(wxcxcProjectAlarmConfigService.updateWxcxcProjectAlarmConfig(wxcxcProjectAlarmConfig));
    }

    /**
     * 删除报警配置
     */
    @PreAuthorize("@ss.hasPermi('iot:projectAlarmConfig')")
    @Log(title = "报警配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcProjectAlarmConfigService.deleteWxcxcProjectAlarmConfigByIds(ids));
    }
}
