/*
author: caiguangqian
create:  2019.4.24
modified:  2019.4.24
description：学历的实体类
*/
package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class LearningDegree {
    //学历表ID
    private Long id;
    //用户ID
    private Long userId;
    //开始时间
    private Date beginTime;
    //结束时间
    private Date endTime;
    //国家ID
    private Long countryId;
    //城市ID
    private Long cityId;
    //学校ID
    private Long schoolId;
    //academic表id
    private Long academicId;
    //academic_degree表id
    private Long degreeId;
    //本记录的创建时间
    private Date datetime;
    //本记录的写入者
    private Long byWho;
    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    @Override
    public String toString() {
        return "LearningDegree{" +
                "id=" + id +
                ", userId=" + userId +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", countryId=" + countryId +
                ", cityId=" + cityId +
                ", schoolId=" + schoolId +
                ", academicId=" + academicId +
                ", degreeId=" + degreeId +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
    //获取学历表ID
    public Long getId() {
        return id;
    }
    //写入学历表ID
    public void setId(Long id) {
        this.id = id;
    }
    //获取用户ID
    public Long getUserId() {
        return userId;
    }
    //写入用户ID
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Long academicId) {
        this.academicId = academicId;
    }

    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
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