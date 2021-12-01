package com.ruoyi.wxcxc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.wxcxc.domain.WxcxcDevice;
import com.ruoyi.wxcxc.domain.WxcxcDeviceSensor;
import com.ruoyi.wxcxc.dto.WxcxcProjectDeviceSensorDataDto;
import com.ruoyi.wxcxc.dto.WxcxcProjectDeviceSensorDataListDto;
import com.ruoyi.wxcxc.service.IWxcxcDeviceSensorService;
import com.ruoyi.wxcxc.service.IWxcxcDeviceService;
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
public class WxcxcProjectDeviceSensorDataController extends BaseController {
    @Autowired
    private IWxcxcProjectDeviceSensorDataService wxcxcProjectDeviceSensorDataService;
    @Autowired
    private IWxcxcDeviceService wxcxcDeviceService;
    @Autowired
    private IWxcxcDeviceSensorService wxcxcDeviceSensorService;

    // 获取设备数据
    @PreAuthorize("@ss.hasPermi('iot:console')")
    @PostMapping("/list")
    public List<WxcxcProjectDeviceSensorDataListDto> projectDeviceList(@RequestBody WxcxcProjectDeviceSensorDataDto wxcxcProjectDeviceSensorDataDto) {
        if (null == wxcxcProjectDeviceSensorDataDto.getDeviceId()) {
            return null;
        }

        WxcxcProjectDeviceSensorData wxcxcProjectDeviceSensorDataCondition = new WxcxcProjectDeviceSensorData();
        wxcxcProjectDeviceSensorDataCondition.setParams(wxcxcProjectDeviceSensorDataDto.getParams());

        List<WxcxcProjectDeviceSensorData> list = wxcxcProjectDeviceSensorDataService.selectWxcxcProjectDeviceSensorDataList(wxcxcProjectDeviceSensorDataDto.getDeviceId(), wxcxcProjectDeviceSensorDataCondition);

        Map<Long, List<WxcxcProjectDeviceSensorData>> resMap = new HashMap<>();

        for (WxcxcProjectDeviceSensorData item : list) {
            // 判断是否已经存在
            List<WxcxcProjectDeviceSensorData> sensorDataList = resMap.get(item.getSensorId());
            if (null == sensorDataList) sensorDataList = new ArrayList<>();

            sensorDataList.add(item);

            resMap.put(item.getSensorId(), sensorDataList);
        }

        List<WxcxcProjectDeviceSensorDataListDto> returnList = new ArrayList<>();

        for (Map.Entry<Long, List<WxcxcProjectDeviceSensorData>> entry : resMap.entrySet()) {
            WxcxcProjectDeviceSensorDataListDto wxcxcProjectDeviceSensorDataListDto = new WxcxcProjectDeviceSensorDataListDto();

            WxcxcDeviceSensor wxcxcDeviceSensor = wxcxcDeviceSensorService.selectWxcxcDeviceSensorById(entry.getKey());

            if (null == wxcxcDeviceSensor) continue;

            wxcxcProjectDeviceSensorDataListDto.setId(wxcxcDeviceSensor.getId());
            wxcxcProjectDeviceSensorDataListDto.setDeviceId(wxcxcDeviceSensor.getDeviceId());
            wxcxcProjectDeviceSensorDataListDto.setName(wxcxcDeviceSensor.getName());
            wxcxcProjectDeviceSensorDataListDto.setIconFile(wxcxcDeviceSensor.getIconFile());
            wxcxcProjectDeviceSensorDataListDto.setSort(wxcxcDeviceSensor.getSort());
            wxcxcProjectDeviceSensorDataListDto.setUnit(wxcxcDeviceSensor.getUnit());
            wxcxcProjectDeviceSensorDataListDto.setType(wxcxcDeviceSensor.getType());

            wxcxcProjectDeviceSensorDataListDto.setDataList(entry.getValue());

            returnList.add(wxcxcProjectDeviceSensorDataListDto);
        }

        return returnList;
    }
}
