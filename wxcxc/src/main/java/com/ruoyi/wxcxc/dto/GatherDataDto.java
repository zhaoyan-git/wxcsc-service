package com.ruoyi.wxcxc.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class GatherDataDto {
    private String calcData;
    private Date dateTime;

    private String data;
    private Date createTime;

    public String getData() {
        return calcData;
    }

    public Date getCreateTime() {
        return dateTime;
    }
}
