package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectPointGroup;
import com.ruoyi.wxcxc.dto.ProjectPointGroupDto;
import com.ruoyi.wxcxc.dto.WxcxcProjectPointDto;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointGroupService;
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
 * @date 2021-11-01
 */
@RestController
@RequestMapping("/iot/projectPoint")
public class WxcxcProjectPointController extends BaseController {
    @Autowired
    private IWxcxcProjectPointService wxcxcProjectPointService;
    @Autowired
    private IWxcxcProjectPointGroupService wxcxcProjectPointGroupService;

    /**
     * 查询项目测点列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint')")
    @GetMapping("/list")
    public TableDataInfo list(WxcxcProjectPoint wxcxcProjectPoint) {
        if (null == wxcxcProjectPoint.getProjectStructureId()) return getDataTable(new ArrayList<>());

        List<ProjectPointGroupDto> returnList = new ArrayList<>();

        // 获取未分组的测点
        List<WxcxcProjectPoint> groupisNullList = wxcxcProjectPointService.selectWxcxcProjectPointByPointGroupIdIsNull(wxcxcProjectPoint.getProjectStructureId());
        if (null != groupisNullList && 0 < groupisNullList.size()) {
            ProjectPointGroupDto projectPointGroupDto = new ProjectPointGroupDto();
            projectPointGroupDto.setId(-1l);
            projectPointGroupDto.setName("未分组");
            projectPointGroupDto.setStructureId(wxcxcProjectPoint.getProjectStructureId());

            projectPointGroupDto.setChildren(groupisNullList);
            returnList.add(projectPointGroupDto);
        }

        // 获取测点分组
        WxcxcProjectPointGroup wxcxcProjectPointGroup = new WxcxcProjectPointGroup();
        wxcxcProjectPointGroup.setStructureId(wxcxcProjectPoint.getProjectStructureId());
        List<WxcxcProjectPointGroup> wxcxcProjectPointGroupList = wxcxcProjectPointGroupService.selectWxcxcProjectPointGroupList(wxcxcProjectPointGroup);
        if (null != wxcxcProjectPointGroupList && 0 < wxcxcProjectPointGroupList.size()) {
            for (WxcxcProjectPointGroup item : wxcxcProjectPointGroupList) {
                ProjectPointGroupDto projectPointGroupDto = new ProjectPointGroupDto();
                projectPointGroupDto.setId(item.getId());
                projectPointGroupDto.setName(item.getName());
                projectPointGroupDto.setStructureId(item.getStructureId());

                // 获取该分组下测点
                WxcxcProjectPoint wxcxcProjectPointCondition = new WxcxcProjectPoint();
                wxcxcProjectPointCondition.setProjectStructureId(wxcxcProjectPoint.getProjectStructureId());
                wxcxcProjectPointCondition.setPointGroupId(item.getId());
                projectPointGroupDto.setChildren(wxcxcProjectPointService.selectWxcxcProjectPointList(wxcxcProjectPointCondition));
                returnList.add(projectPointGroupDto);
            }
        }

        return getDataTable(returnList);
    }

    /**
     * 导出项目测点列表
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint')")
    @Log(title = "项目测点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WxcxcProjectPoint wxcxcProjectPoint) {
        List<WxcxcProjectPoint> list = wxcxcProjectPointService.selectWxcxcProjectPointList(wxcxcProjectPoint);
        ExcelUtil<WxcxcProjectPoint> util = new ExcelUtil<WxcxcProjectPoint>(WxcxcProjectPoint.class);
        return util.exportExcel(list, "项目测点数据");
    }

    /**
     * 获取项目测点详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxcxcProjectPointService.selectWxcxcProjectPointById(id));
    }

    /**
     * 新增项目测点
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint')")
    @Log(title = "项目测点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxcxcProjectPointDto wxcxcProjectPointDto) {
        WxcxcProjectPoint wxcxcProjectPoint = new WxcxcProjectPoint();

        wxcxcProjectPoint.setName(wxcxcProjectPointDto.getName());
        wxcxcProjectPoint.setPhotoFile(wxcxcProjectPointDto.getPhotoFile());
        wxcxcProjectPoint.setProjectStructureId(wxcxcProjectPointDto.getProjectStructureId());
        wxcxcProjectPoint.setPointGroupId(wxcxcProjectPointDto.getPointGroupId());
        wxcxcProjectPoint.setAlarmFlag(wxcxcProjectPointDto.getAlarmFlag());
        wxcxcProjectPoint.setMonitorType(wxcxcProjectPointDto.getMonitorType());
        wxcxcProjectPoint.setMonitorData(wxcxcProjectPointDto.getMonitorData().toJSONString());
        wxcxcProjectPoint.setDelFlag("0");

        return toAjax(wxcxcProjectPointService.insertWxcxcProjectPoint(wxcxcProjectPoint));
    }

    /**
     * 修改项目测点
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint')")
    @Log(title = "项目测点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxcxcProjectPointDto wxcxcProjectPointDto) {
        WxcxcProjectPoint wxcxcProjectPoint = new WxcxcProjectPoint();

        wxcxcProjectPoint.setId(wxcxcProjectPointDto.getId());
        wxcxcProjectPoint.setName(wxcxcProjectPointDto.getName());
        wxcxcProjectPoint.setPhotoFile(wxcxcProjectPointDto.getPhotoFile());
        wxcxcProjectPoint.setProjectStructureId(wxcxcProjectPointDto.getProjectStructureId());
        wxcxcProjectPoint.setPointGroupId(wxcxcProjectPointDto.getPointGroupId());
        wxcxcProjectPoint.setAlarmFlag(wxcxcProjectPointDto.getAlarmFlag());
        wxcxcProjectPoint.setMonitorType(wxcxcProjectPointDto.getMonitorType());
        wxcxcProjectPoint.setMonitorData(wxcxcProjectPointDto.getMonitorData().toJSONString());

        return toAjax(wxcxcProjectPointService.updateWxcxcProjectPoint(wxcxcProjectPoint));
    }

    /**
     * 删除项目测点
     */
    @PreAuthorize("@ss.hasPermi('iot:projectPoint')")
    @Log(title = "项目测点", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxcxcProjectPointService.deleteWxcxcProjectPointByIds(ids));
    }
}
