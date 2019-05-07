package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class UserinfoApplyApproval {
    private Long id;

    private Long universityId;

    private Long userinfoApplyId;

    private Integer step;

    private Boolean result;

    private String reason;

    private Long checkWho;

    private Date checkTime;

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

    public Long getUserinfoApplyId() {
        return userinfoApplyId;
    }

    public void setUserinfoApplyId(Long userinfoApplyId) {
        this.userinfoApplyId = userinfoApplyId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Long getCheckWho() {
        return checkWho;
    }

    public void setCheckWho(Long checkWho) {
        this.checkWho = checkWho;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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