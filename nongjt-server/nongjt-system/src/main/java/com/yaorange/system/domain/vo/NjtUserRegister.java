package com.yaorange.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yaorange.common.annotation.Excel;
import com.yaorange.common.core.domain.BaseEntity;

/**
 * 用户注册信息对象 njt_user_register
 *
 * @author yanye
 * @date 2025-09-18
 */
public class NjtUserRegister extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 主体名称 */
    @Excel(name = "主体名称")
    private String companyName;

    /** 注册手机号 */
    @Excel(name = "注册手机号")
    private String mobilePhone;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerTime;

    /** 是否会员（0-否 1-是） */
    @Excel(name = "是否会员", readConverterExp = "0=否,1=是")
    private Long memberFlag;

    /** 信息是否完善（0-否 1-是） */
    @Excel(name = "信息是否完善", readConverterExp = "0=未完善,1=已完善")
    private Long perfectFlag;

    /** 信息完善时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "信息完善时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date perfectTime;

    /** 试用到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试用到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date probationTime;

    /** 购买会员时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买会员时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date memberTime;

    /** 会员过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会员过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date memberExpiredTime;

    /** 删除标记（1-删除 0-正常 ） */
    private Long delFlag;

    /** 用户状态 */
    @Excel(name = "用户状态")
    private Long userStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setRegisterTime(Date registerTime)
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime()
    {
        return registerTime;
    }

    public void setMemberFlag(Long memberFlag)
    {
        this.memberFlag = memberFlag;
    }

    public Long getMemberFlag()
    {
        return memberFlag;
    }

    public void setPerfectFlag(Long perfectFlag)
    {
        this.perfectFlag = perfectFlag;
    }

    public Long getPerfectFlag()
    {
        return perfectFlag;
    }

    public void setPerfectTime(Date perfectTime)
    {
        this.perfectTime = perfectTime;
    }

    public Date getPerfectTime()
    {
        return perfectTime;
    }

    public void setProbationTime(Date probationTime)
    {
        this.probationTime = probationTime;
    }

    public Date getProbationTime()
    {
        return probationTime;
    }

    public void setMemberTime(Date memberTime)
    {
        this.memberTime = memberTime;
    }

    public Date getMemberTime()
    {
        return memberTime;
    }

    public void setMemberExpiredTime(Date memberExpiredTime)
    {
        this.memberExpiredTime = memberExpiredTime;
    }

    public Date getMemberExpiredTime()
    {
        return memberExpiredTime;
    }

    public void setDelFlag(Long delFlag)
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag()
    {
        return delFlag;
    }

    public void setUserStatus(Long userStatus)
    {
        this.userStatus = userStatus;
    }

    public Long getUserStatus()
    {
        return userStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyName", getCompanyName())
            .append("mobilePhone", getMobilePhone())
            .append("password", getPassword())
            .append("registerTime", getRegisterTime())
            .append("memberFlag", getMemberFlag())
            .append("perfectFlag", getPerfectFlag())
            .append("perfectTime", getPerfectTime())
            .append("probationTime", getProbationTime())
            .append("memberTime", getMemberTime())
            .append("memberExpiredTime", getMemberExpiredTime())
            .append("delFlag", getDelFlag())
            .append("userStatus", getUserStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
