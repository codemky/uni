package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class UserinfoApply {
    private Long id;

    private Long universityId;

    private Long approvalMainId;

    private Integer infoType;

    private Long oldInfoId;

    private Long newInfoId;

    private Date startTime;

    private Date endTime;

    private String applyReason;

    private Date datetime;

    private Long byWho;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Long getApprovalMainId() {
        return approvalMainId;
    }

    public void setApprovalMainId(Long approvalMainId) {
        this.approvalMainId = approvalMainId;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Long getOldInfoId() {
        return oldInfoId;
    }

    public void setOldInfoId(Long oldInfoId) {
        this.oldInfoId = oldInfoId;
    }

    public Long getNewInfoId() {
        return newInfoId;
    }

    public void setNewInfoId(Long newInfoId) {
        this.newInfoId = newInfoId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
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