/**
 * Author: mokuanyuan 16:28 2019/4/30
 * @apiNote: 用户信息申请审核流程表实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserinfoApplyApproval {
//    审批流程表id
    private Long id;
//    学校ID
    private Long universityId;
//    申请表id
    private Long userinfoApplyId;
//    步骤编号：说明是第几次审批
    private Integer step;
//    结果，0：不通过 1：通过
    private Boolean result;
//    审批人
    private Long checkWho;
//    审批时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;
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