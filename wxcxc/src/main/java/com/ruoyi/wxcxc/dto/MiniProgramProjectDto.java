package com.ruoyi.wxcxc.dto;

import lombok.Data;

@Data
public class MiniProgramProjectDto {
    private Long id;

    /** 项目名称 */
    private String name;

    /** 所属企业 */
    private Long businessId;

    /** 监控目的 */
    private String aim;

    /** 项目图片 */
    private String photoFile;

    /** 备注 */
    private String details;

    /** 项目位置纬度 */
    private String positionLat;

    /** 项目位置经度 */
    private String positionLng;

    /** 警报标志（0表示未告警 1表示告警） */
    private String alarmFlag;

    private String delFlag;

    // 结构物
    private Integer structureCount;

    // 测点数
    private Integer pointCount;

    // DTO数
    private Integer dtoCount;

    // 报警数
    private Integer alarmCount;
}
