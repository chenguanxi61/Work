package com.yaorange.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yaorange.common.annotation.Excel;
import com.yaorange.common.annotation.Excel.ColumnType;
import com.yaorange.common.core.domain.BaseEntity;

/**
 * 一体打印机服务点表 njt_ytj_pos
 * 
 * @author ruoyi
 */
public class NjtYtjPos extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键", cellType = ColumnType.NUMERIC)
    private Long id;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区/县 */
    @Excel(name = "区/县")
    private String area;

    /** 乡镇/街道 */
    @Excel(name = "乡镇/街道")
    private String country;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailAddress;

    /** 自主服务点名称 */
    @Excel(name = "自主服务点名称")
    @NotBlank(message = "自主服务点名称不能为空")
    @Size(min = 0, max = 64, message = "自主服务点名称长度不能超过64个字符")
    private String posName;

    /** 序列号 */
    @Excel(name = "序列号")
    @NotBlank(message = "序列号不能为空")
    @Size(min = 0, max = 64, message = "序列号长度不能超过64个字符")
    private String serialCode;

    /** 状态 0-停用 1-正常 */
    @Excel(name = "状态", readConverterExp = "0=停用,1=正常")
    private Integer status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getDetailAddress()
    {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress)
    {
        this.detailAddress = detailAddress;
    }

    public String getPosName()
    {
        return posName;
    }

    public void setPosName(String posName)
    {
        this.posName = posName;
    }

    public String getSerialCode()
    {
        return serialCode;
    }

    public void setSerialCode(String serialCode)
    {
        this.serialCode = serialCode;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
            .append("country", getCountry())
            .append("detailAddress", getDetailAddress())
            .append("posName", getPosName())
            .append("serialCode", getSerialCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}