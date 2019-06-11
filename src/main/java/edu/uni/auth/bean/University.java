package edu.uni.auth.bean;

import java.util.Date;

public class University {
    private Long id;

    private String unitNumber;

    private String socialTrustCode;

    private String certificationCode;

    private String enterpriseCode;

    private String name;

    private String ename;

    private Integer status;

    private Integer fundingSources;

    private Date establishDate;

    private Integer hostedBy;

    private Integer adminiBy;

    private Integer initialFunding;

    private Date certificationBeginDate;

    private Date certificationEndDate;

    private String telephone;

    private String address;

    private Date datetime;

    private Long byWho;

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

    public Date getCertificationBeginDate() {
        return certificationBeginDate;
    }

    public void setCertificationBeginDate(Date certificationBeginDate) {
        this.certificationBeginDate = certificationBeginDate;
    }

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