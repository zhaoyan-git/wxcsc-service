package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.wxcxc.util.CRCUtils;
import com.ruoyi.wxcxc.util.HexUtil;
import lombok.Data;

@Data
public class ModbusRtuOrderDto {
    /**
     * 从站地址
     */
    private String slaveUnitId;

    /**
     * 功能码
     */
    private String slaveCode;

    /**
     * 地址Hi
     */
    private String slaveAddressHi;

    /**
     * 地址Lo
     */
    private String slaveAddressLo;

    /**
     * 数量Hi
     */
    private String slaveAmountHi;

    /**
     * 数量Lo
     */
    private String slaveAmountLo;

    @Override
    public String toString() {
        String str = "";

        str += slaveUnitId;
        // 功能码
        str += slaveCode;
        // 起始寄存器地址
        str += slaveAddressHi + slaveAddressLo;
        // 读取寄存器个数
        str += slaveAmountHi + slaveAmountLo;


        str += CRCUtils.getCRC(HexUtil.hexToByte(str));

        return str;
    }
}
