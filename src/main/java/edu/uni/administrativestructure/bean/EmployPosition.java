package edu.uni.administrativestructure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * author:黄育林
 * create:2019.5.7
 * modified:2019.5.9
 * 功能：雇员岗位实体类
 */
public class EmployPosition {
    //    主id
    private Long id;
    //    学校id
    private Long universityId;
    //    雇员id
    private Long employId;
    //    岗位id
    private Long positionId;
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

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Long getEmployId() {
        return employId;
    }

    public void setEmployId(Long employId) {
        this.employId = employId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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