/**
 * Author: mokuanyuan 15:55 2019/4/30
 * @apiNote: 审批步骤数规定表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ApprovalMain {

//    审批步数规定表id
    private Long id;
//    学校id
    private Long universityId;
//    审批业务名称
    private String name;
//    审批所需总步数 这项申请所需要的总步数来确定是否完成审批
    private Integer stepCnt;
//    审批业务类型 按类型分组有利于组织管理。如：教学类、生活类、课外活动类，或者一个模块一个类别
    private String type;
//    本记录创建时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
//    本记录写入者 用户id
    private Long byWho;
//    本记录是否有效 0：有效  1：无效
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStepCnt() {
        return stepCnt;
    }

    public void setStepCnt(Integer stepCnt) {
        this.stepCnt = stepCnt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    @Override
    public String toString() {
        return "ApprovalMain{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", name='" + name + '\'' +
                ", stepCnt=" + stepCnt +
                ", type='" + type + '\'' +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}