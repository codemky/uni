/**
 * Author: mokuanyuan 16:15 2019/4/30
 * @apiNote: 用户信息申请表实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserinfoApply {
//    用户信息申请表id
    private Long id;
//    学校ID
    private Long universityId;
//    审批业务ID
    private Long approvalMainId;
//    指定要审核的信息种类  0:联系方式  1:地址 2：照片  3：亲属  4：学历  5：简历  6：学生信息 7：教职工信息 8：用户个人信息 9：学生excel表  10：职员excel表
    private Integer infoType;
//    旧信息记录的id
    private Long oldInfoId;
//    新信息记录的id
    private Long newInfoId;
//    开始时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
//    结束时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
//    说明提出更新用户信息的原因
    private String applyReason;
//    申请结果：0：不通过 1：通过
    private Boolean applyResult;
//    本记录的创建时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
//    本记录的写入者
    private Long byWho;
//    本记录是否有效 0:有效 1:无效
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

    public Boolean getApplyResult() {
        return applyResult;
    }

    public void setApplyResult(Boolean applyResult) {
        this.applyResult = applyResult;
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