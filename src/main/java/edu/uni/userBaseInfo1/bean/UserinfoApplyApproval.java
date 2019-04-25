package edu.uni.userBaseInfo1.bean;
/*
author:  Arvin
create:  2019.4.5
modified:  2019.4.24
description：用户信息审批流程表的实体类
*/
import java.util.Date;
/*
用户信息审批流程表实体类
 */
public class UserinfoApplyApproval {
    //数据表的唯一编号id
    private Long id;

    //学校id
    private Long universityId;

    //申请表id
    private Long userinfoApplyId;

    //步骤编号：说明是第几次审批
    private Integer step;

    //结果，0：不通过 1：通过
    private Boolean result;

    //审批人
    private Long checkWho;

    //审批时间
    private Date checkTime;

    //本记录的创建时间
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    //无参构造器
    public UserinfoApplyApproval() {
    }

    //有参构造方法
    public UserinfoApplyApproval(Long id, Long universityId, Long userinfoApplyId, Integer step, Boolean result, Long checkWho, Date checkTime, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.universityId = universityId;
        this.userinfoApplyId = userinfoApplyId;
        this.step = step;
        this.result = result;
        this.checkWho = checkWho;
        this.checkTime = checkTime;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    //获取数据表的唯一编号id
    public Long getId() {
        return id;
    }

    //写获取数据表的唯一编号id
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

    //获取申请表id
    public Long getUserinfoApplyId() {
        return userinfoApplyId;
    }

    //写申请表id
    public void setUserinfoApplyId(Long userinfoApplyId) {
        this.userinfoApplyId = userinfoApplyId;
    }

    //获取步骤编号：说明是第几次审批
    public Integer getStep() {
        return step;
    }

    //写步骤编号：说明是第几次审批
    public void setStep(Integer step) {
        this.step = step;
    }

    //获取结果，0：不通过 1：通过
    public Boolean getResult() {
        return result;
    }

    //写结果，0：不通过 1：通过
    public void setResult(Boolean result) {
        this.result = result;
    }

    //获取审批人
    public Long getCheckWho() {
        return checkWho;
    }

    //写审批人
    public void setCheckWho(Long checkWho) {
        this.checkWho = checkWho;
    }

    //获取本记录的审批时间
    public Date getCheckTime() {
        return checkTime;
    }

    //写本记录的审批时间
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    //获取本记录的创建时间
    public Date getDatetime() {
        return datetime;
    }

    //写本记录的创建时间
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

    //获取本记录是否有效 0:有效 1:无效
    public Boolean getDeleted() {
        return deleted;
    }

    //写本记录是否有效 0:有效 1:无效
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    //用户信息审批流程表的各项信息
    public java.lang.String toString() {
        return "UserinfoApplyApproval{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", userinfoApplyId=" + userinfoApplyId +
                ", step=" + step +
                ", result=" + result +
                ", checkWho=" + checkWho +
                ", checkTime=" + checkTime +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}