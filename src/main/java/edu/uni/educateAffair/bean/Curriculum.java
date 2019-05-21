package edu.uni.educateAffair.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Curriculum {
    private Long id;

    private Long universityId;
    //校历ID
    private Long canlendarId;
    //作息表ID
    private Long timeTableId;
    //场地ID
    private Long fieldId;
    //班级ID
    private Long classId;
    //教师ID
    private Long employeeId;
    //课程ID
    private Long courseId;

    private Integer status;

    private Date datetime;

    private Long byWho;

    private Integer deleted;

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

    public Long getCanlendarId() {
        return canlendarId;
    }

    public void setCanlendarId(Long canlendarId) {
        this.canlendarId = canlendarId;
    }

    public Long getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(Long timeTableId) {
        this.timeTableId = timeTableId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", canlendarId=" + canlendarId +
                ", timeTableId=" + timeTableId +
                ", fieldId=" + fieldId +
                ", classId=" + classId +
                ", employeeId=" + employeeId +
                ", courseId=" + courseId +
                ", status=" + status +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}