package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserinfoApplyApproval {
    private Long id;
    private Long universityId;
    private Long userinfoApplyId;
    private int step;
    private boolean result;
    private String reason;
    private Long checkWho;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
    private Long byWho;
    private boolean deleted;
    private String roleName;
    private int infoType;
    private Long applyUserId;

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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getInfoType() {
        return infoType;
    }

    public void setInfoType(int infoType) {
        this.infoType = infoType;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public UserinfoApplyApproval() {
    }

    public UserinfoApplyApproval(Long id, Long universityId, Long userinfoApplyId, int step, boolean result, String reason, Long checkWho, Date checkTime, Date datetime, Long byWho, boolean deleted, String roleName, int infoType, Long applyUserId) {
        this.id = id;
        this.universityId = universityId;
        this.userinfoApplyId = userinfoApplyId;
        this.step = step;
        this.result = result;
        this.reason = reason;
        this.checkWho = checkWho;
        this.checkTime = checkTime;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
        this.roleName = roleName;
        this.infoType = infoType;
        this.applyUserId = applyUserId;
    }

    @Override
    public String toString() {
        return "UserinfoApplyApproval{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", userinfoApplyId=" + userinfoApplyId +
                ", step=" + step +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                ", checkWho=" + checkWho +
                ", checkTime=" + checkTime +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                ", roleName='" + roleName + '\'' +
                ", infoType=" + infoType +
                ", applyUserId=" + applyUserId +
                '}';
    }
}