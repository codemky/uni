/**
 * Author: mokuanyuan 15:56 2019/4/30
 * @apiNote: 审批步骤详情表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ApprovalStepIncharge {
//    审批步骤详情表id
    private Long id;
//    学校id
    private Long universityId;
//    审批步数规定表id
    private Long approvalMainId;
//    步骤名称
    private String name;
//    步骤编号
    private Integer step;
//    角色表id  说明该申请的当前步骤需要什么角色审批
    private Long roleId;
//    本记录创建时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
//    本记录的写入者 用户id
    private Long byWho;
//    本记录是否有效 0：有效  1：无效
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public ApprovalStepIncharge() {
    }

    public ApprovalStepIncharge(Long id, Long universityId, Long approvalMainId, String name, Integer step, Long roleId, Date datetime, Long byWho, Boolean deleted, String roleName, Integer infoType, Long applyUserId) {
        this.id = id;
        this.universityId = universityId;
        this.approvalMainId = approvalMainId;
        this.name = name;
        this.step = step;
        this.roleId = roleId;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ApprovalStepIncharge{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", approvalMainId=" + approvalMainId +
                ", name='" + name + '\'' +
                ", step=" + step +
                ", roleId=" + roleId +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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