package com.ruoyi.wxcxc.controller;

import java.util.List;

import com.ruoyi.wxcxc.domain.WxcxcProjectPointGatherConfig;
import com.ruoyi.wxcxc.dto.GatherDataDto;
import com.ruoyi.wxcxc.dto.WxcxcProjectPointDataDto;
import com.ruoyi.wxcxc.dto.WxcxcProjectPointGatherConfigDto;
import com.ruoyi.wxcxc.mapper.WxcxcProjectPointDataMapper;
import com.ruoyi.wxcxc.service.IWxcxcProjectPointGatherConfigService;
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
    @Autowired
    private WxcxcProjectPointDataMapper wxcxcProjectPointDataMapper;
    @Autowired
    private IWxcxcProjectPointGatherConfigService wxcxcProjectPointGatherConfigService;

    // 获取测点数据
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @PostMapping("/list")
    public List projectDeviceList(@RequestBody WxcxcProjectPointDataDto wxcxcProjectPointDataDto) {
        if (null == wxcxcProjectPointDataDto.getStructureId() || null == wxcxcProjectPointDataDto.getPointId()) {
            return null;
        }

        if ("1".equals(wxcxcProjectPointDataDto.getGatherType())) {
            // 返回实时数据
            WxcxcProjectPointData wxcxcProjectPointData = new WxcxcProjectPointData();
            wxcxcProjectPointData.setParams(wxcxcProjectPointDataDto.getParams());
            wxcxcProjectPointData.setPointId(wxcxcProjectPointDataDto.getPointId());

            List<WxcxcProjectPointData> list = wxcxcProjectPointDataService.selectWxcxcProjectPointDataList(wxcxcProjectPointDataDto.getStructureId(), wxcxcProjectPointData);

            return list;
        } else {
            // 根据测点id获取聚集配置
            WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfigCondition = new WxcxcProjectPointGatherConfigDto();
            wxcxcProjectPointGatherConfigCondition.setPointId(wxcxcProjectPointDataDto.getPointId());
            List<WxcxcProjectPointGatherConfig> wxcxcProjectPointGatherConfigList = wxcxcProjectPointGatherConfigService.selectWxcxcProjectPointGatherConfigList(wxcxcProjectPointGatherConfigCondition);

            if (null == wxcxcProjectPointGatherConfigList || 0 == wxcxcProjectPointGatherConfigList.size()) return null;

            WxcxcProjectPointData wxcxcProjectPointData = new WxcxcProjectPointData();
            wxcxcProjectPointData.setParams(wxcxcProjectPointDataDto.getParams());
            wxcxcProjectPointData.setPointId(wxcxcProjectPointDataDto.getPointId());

            // 返回聚集数据
            List<GatherDataDto> list = wxcxcProjectPointDataMapper.selectGatherDataDtoList(
                    wxcxcProjectPointDataDto.getStructureId(),
                    wxcxcProjectPointGatherConfigList.get(0).getCalcType(),
                    wxcxcProjectPointGatherConfigList.get(0).getCycle(),
                    wxcxcProjectPointData);

            return list;
        }

    }

    // 获取多个测点数据
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @PostMapping("/list/multiple")
    public List projectDeviceListMultiple(@RequestBody WxcxcProjectPointDataDto wxcxcProjectPointDataDto) {
        if (null == wxcxcProjectPointDataDto.getStructureId() || null == wxcxcProjectPointDataDto.getPointId()) {
            return null;
        }

        if ("1".equals(wxcxcProjectPointDataDto.getGatherType())) {
            // 返回实时数据
            WxcxcProjectPointData wxcxcProjectPointData = new WxcxcProjectPointData();
            wxcxcProjectPointData.setParams(wxcxcProjectPointDataDto.getParams());
            wxcxcProjectPointData.setPointId(wxcxcProjectPointDataDto.getPointId());

            List<WxcxcProjectPointData> list = wxcxcProjectPointDataService.selectWxcxcProjectPointDataList(wxcxcProjectPointDataDto.getStructureId(), wxcxcProjectPointData);

            return list;
        } else {
            // 根据测点id获取聚集配置
            WxcxcProjectPointGatherConfigDto wxcxcProjectPointGatherConfigCondition = new WxcxcProjectPointGatherConfigDto();
            wxcxcProjectPointGatherConfigCondition.setPointId(wxcxcProjectPointDataDto.getPointId());
            List<WxcxcProjectPointGatherConfig> wxcxcProjectPointGatherConfigList = wxcxcProjectPointGatherConfigService.selectWxcxcProjectPointGatherConfigList(wxcxcProjectPointGatherConfigCondition);

            if (null == wxcxcProjectPointGatherConfigList || 0 == wxcxcProjectPointGatherConfigList.size()) return null;

            WxcxcProjectPointData wxcxcProjectPointData = new WxcxcProjectPointData();
            wxcxcProjectPointData.setParams(wxcxcProjectPointDataDto.getParams());
            wxcxcProjectPointData.setPointId(wxcxcProjectPointDataDto.getPointId());

            // 返回聚集数据
            List<GatherDataDto> list = wxcxcProjectPointDataMapper.selectGatherDataDtoList(
                    wxcxcProjectPointDataDto.getStructureId(),
                    wxcxcProjectPointGatherConfigList.get(0).getCalcType(),
                    wxcxcProjectPointGatherConfigList.get(0).getCycle(),
                    wxcxcProjectPointData);

            return list;
        }

    }


}
