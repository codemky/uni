package edu.uni.userBaseInfo1.bean;
/*
author:  laizhouhao
create:  2019.4.5
modified:  2019.4.24
description：用户信息申请表的实体类
*/
import java.util.Date;
/*
用户信息申请表实体类
*/
public class UserinfoApply {
    //数据表唯一编号id
    private Long id;

    //学校id
    private Long universityId;

    //审批业务id
    private Long approvalMainId;

    //指定要审核的信息种类  0:联系方式  1:地址 2：照片  3：亲属  4：学历  5：简历 6：学生信息 7：教职工信息 8：用户个人信息
    private Integer infoType;

    //旧信息记录的id
    private Long oldInfoId;

    //新信息记录的id
    private Long newInfoId;

    //开始时间
    private Date startTime;

    //结束时间0
    private Date endTime;

    //说明提出更新用户信息的原因
    private String applyReason;

    //本记录的创建时间
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    //无参构造方法
    public UserinfoApply() {
    }

    //有参构造方法
    public UserinfoApply(Long id, Long universityId, Long approvalMainId, Integer infoType, Long oldInfoId, Long newInfoId, Date startTime, Date endTime, String applyReason, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.universityId = universityId;
        this.approvalMainId = approvalMainId;
        this.infoType = infoType;
        this.oldInfoId = oldInfoId;
        this.newInfoId = newInfoId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.applyReason = applyReason;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    //获取数据表的唯一id
    public Long getId() {
        return id;
    }

    //写数据表的唯一id
    public void setId(Long id) {
        this.id = id;
    }

    //获取学校id
    public Long getUniversityId() {
        return universityId;
    }

    //写学校id
    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    //获取业务审批id
    public Long getApprovalMainId() {
        return approvalMainId;
    }

    //写业务审批id
    public void setApprovalMainId(Long approvalMainId) {
        this.approvalMainId = approvalMainId;
    }

    //获取指定审批的信息种类
    public Integer getInfoType() {
        return infoType;
    }

    //写指定审批的信息种类
    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    //获取旧信息的记录id
    public Long getOldInfoId() {
        return oldInfoId;
    }

    //写旧信息的记录的id
    public void setOldInfoId(Long oldInfoId) {
        this.oldInfoId = oldInfoId;
    }

    //获取新信息记录的id
    public Long getNewInfoId() {
        return newInfoId;
    }

    //写信息记录的id
    public void setNewInfoId(Long newInfoId) {
        this.newInfoId = newInfoId;
    }

    //获取开始时间
    public Date getStartTime() {
        return startTime;
    }

    //写开始时间
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    //获取结束时间
    public Date getEndTime() {
        return endTime;
    }

    //写结束时间
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    //获取说明提出更新用户信息的原因
    public String getApplyReason() {
        return applyReason;
    }

    //写说明提出更新用户信息的原因
    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    //获取本记录创建的时间
    public Date getDatetime() {
        return datetime;
    }

    //写本记录创建的时间
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    //获取本记录的写入者
    public Long getByWho() {
        return byWho;
    }

    //写本记录的写入者
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    //本记录是否有效 0:有效 1:无效
    public Boolean getDeleted() {
        return deleted;
    }

    //写本记录是否有效 0:有效 1:无效
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    //申请信息表的各项信息
    public java.lang.String toString() {
        return "UserinfoApply{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", approvalMainId=" + approvalMainId +
                ", infoType=" + infoType +
                ", oldInfoId=" + oldInfoId +
                ", newInfoId=" + newInfoId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", applyReason='" + applyReason + '\'' +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}