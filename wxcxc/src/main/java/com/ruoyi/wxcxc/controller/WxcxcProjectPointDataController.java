package com.ruoyi.wxcxc.controller;

import java.util.List;

import com.ruoyi.wxcxc.dto.WxcxcProjectPointDataDto;
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
import com.ruoyi.wxcxc.domain.WxcxcProjectPointData;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测点数据表Controller
 *
 * @author l62202
 * @date 2021-11-10
 */
@RestController
@RequestMapping("/iot/structurePointData")
public class WxcxcProjectPointDataController extends BaseController {
    @Autowired
    private IWxcxcProjectPointDataService wxcxcProjectPointDataService;

    // 获取测点数据
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @PostMapping("/list")
    public List<WxcxcProjectPointData> projectDeviceList(@RequestBody WxcxcProjectPointDataDto wxcxcProjectPointDataDto) {
        if (null == wxcxcProjectPointDataDto.getStructureId() || null == wxcxcProjectPointDataDto.getPointId()) {
            return null;
        }

        WxcxcProjectPointData wxcxcProjectPointData = new WxcxcProjectPointData();
        wxcxcProjectPointData.setParams(wxcxcProjectPointData.getParams());
        wxcxcProjectPointData.setPointId(wxcxcProjectPointDataDto.getPointId());

        List<WxcxcProjectPointData> list = wxcxcProjectPointDataService.selectWxcxcProjectPointDataList(wxcxcProjectPointDataDto.getStructureId(), wxcxcProjectPointData);

        return list;
    }
}
