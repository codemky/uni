package edu.uni.administrativestructure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * author:黄育林
 * create:2019.5.7
 * modified:2019.5.9
 * 功能：学校实体类
 */
public class University {
    //    主id
    private Long id;
    //    学校事业单位编号
    private String unitNumber;
    //    统一社会信用代码
    private String socialTrustCode;
    //    证书识别码
    private String certificationCode;
    //    事证号
    private String enterpriseCode;
    //    学校名称
    private String name;
    //    英文名
    private String ename;
    //    单位状态
    private Integer status;
    //    经费来源
    private Integer fundingSources;
    //    设立登记时间
    private Date establishDate;
    //    举办单位
    private Integer hostedBy;
    //    登记管理机关
    private Integer adminiBy;
    //    开办资金
    private Integer initialFunding;
    //    证书有效期
    private Date certificationBeginDate;
    //    证书有效期
    private Date certificationEndDate;
    //    电话
    private String telephone;
    //    地址
    private String address;
    //    录入日期
    private Date datetime;
    //    录入人
    private Long byWho;
    //    是否有效
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber == null ? null : unitNumber.trim();
    }

    public String getSocialTrustCode() {
        return socialTrustCode;
    }

    public void setSocialTrustCode(String socialTrustCode) {
        this.socialTrustCode = socialTrustCode == null ? null : socialTrustCode.trim();
    }

    public String getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(String certificationCode) {
        this.certificationCode = certificationCode == null ? null : certificationCode.trim();
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFundingSources() {
        return fundingSources;
    }

    public void setFundingSources(Integer fundingSources) {
        this.fundingSources = fundingSources;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Integer getHostedBy() {
        return hostedBy;
    }

    public void setHostedBy(Integer hostedBy) {
        this.hostedBy = hostedBy;
    }

    public Integer getAdminiBy() {
        return adminiBy;
    }

    public void setAdminiBy(Integer adminiBy) {
        this.adminiBy = adminiBy;
    }

    public Integer getInitialFunding() {
        return initialFunding;
    }

    public void setInitialFunding(Integer initialFunding) {
        this.initialFunding = initialFunding;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCertificationBeginDate() {
        return certificationBeginDate;
    }

    public void setCertificationBeginDate(Date certificationBeginDate) {
        this.certificationBeginDate = certificationBeginDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCertificationEndDate() {
        return certificationEndDate;
    }

    public void setCertificationEndDate(Date certificationEndDate) {
        this.certificationEndDate = certificationEndDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}