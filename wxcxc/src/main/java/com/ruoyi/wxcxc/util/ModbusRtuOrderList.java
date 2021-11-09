package com.ruoyi.wxcxc.util;

import com.ruoyi.wxcxc.dto.ModbusRtuOrderDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModbusRtuOrderList {
    private List<ModbusRtuOrderDto> modbusRtuOrderDtoList = new ArrayList<>();

    // 添加新指令
    public void addOrder(String slaveUnitId, String slaveCode, String slaveAddressHi,
                         String slaveAddressLo, String slaveAmountHi, String slaveAmountLo) {

        ModbusRtuOrderDto modbusRtuOrderDto = null;


        // 检查是否有相同从站地址和功能码的指令
        int i = 0;
        for (; i < modbusRtuOrderDtoList.size(); i++) {
            ModbusRtuOrderDto item = modbusRtuOrderDtoList.get(i);
            if (slaveUnitId.equals(item.getSlaveUnitId())) {
                //从站地址相同

                //判断功能码是否相同
                if (slaveCode.equals(item.getSlaveCode())) {
                    modbusRtuOrderDto = item;
                    break;
                }
            }
        }

        if (null == modbusRtuOrderDto) {
            // 添加新指令
            modbusRtuOrderDto = new ModbusRtuOrderDto();

            modbusRtuOrderDto.setSlaveUnitId(slaveUnitId);
            modbusRtuOrderDto.setSlaveCode(slaveCode);
            modbusRtuOrderDto.setSlaveAddressHi(slaveAddressHi);
            modbusRtuOrderDto.setSlaveAddressLo(slaveAddressLo);
            modbusRtuOrderDto.setSlaveAmountHi(slaveAmountHi);
            modbusRtuOrderDto.setSlaveAmountLo(slaveAmountLo);

            modbusRtuOrderDtoList.add(modbusRtuOrderDto);
        } else {
            // 修改指令
            // 新指令的最终长度是否大于旧指令的
            int newAddress = Integer.parseInt(slaveAddressHi + slaveAddressLo, 16);
            int newAmount = Integer.parseInt(slaveAmountHi + slaveAmountLo, 16);

            int address = Integer.parseInt(modbusRtuOrderDto.getSlaveAddressHi() + modbusRtuOrderDto.getSlaveAddressLo(), 16);
            int amount = Integer.parseInt(modbusRtuOrderDto.getSlaveAmountHi() + modbusRtuOrderDto.getSlaveAmountLo(), 16);

            if (newAddress + newAmount > address + amount) {
                // 修改指令
                String str = Integer.toHexString(newAddress + newAmount);
                if (8 > str.length()) {

                    for (int j = 0; j < 8 - str.length(); j++) {
                        str = "0" + str;
                    }
                }

                modbusRtuOrderDto.setSlaveAmountHi(str.substring(0, 3));
                modbusRtuOrderDto.setSlaveAmountLo(str.substring(4, 7));

                modbusRtuOrderDtoList.remove(i);
                modbusRtuOrderDtoList.add(modbusRtuOrderDto);
            }
        }
    }
}
