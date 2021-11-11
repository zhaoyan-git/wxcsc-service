package com.ruoyi.wxcxc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测点数据表对象 wxcxc_project_point_data
 *
 * @author l62202
 * @date 2021-11-10
 */
public class WxcxcProjectPointData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 所属测点
     */
    @Excel(name = "所属测点")
    private Long pointId;

    /**
     * 数据
     */
    @Excel(name = "数据")
    private String data;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sensorId", getPointId())
                .append("data", getData())
                .append("createTime", getCreateTime())
                .toString();
    }
}
