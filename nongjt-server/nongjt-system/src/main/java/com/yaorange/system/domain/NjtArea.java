package com.yaorange.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yaorange.common.core.domain.BaseEntity;

/**
 * 地区层级关系表 njt_area
 *
 * @author ruoyi
 */
public class NjtArea extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 区域编码 */
    private String code;

    /** 父编码（顶级为0） */
    private String parentCode;

    /** 名称 */
    private String name;

    /** 层级标识 0:市 1:区/县 2:街道 */
    private Integer unit;

    /** 状态 0:禁用 1:可用 */
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("code", getCode())
                .append("parentCode", getParentCode())
                .append("name", getName())
                .append("unit", getUnit())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}