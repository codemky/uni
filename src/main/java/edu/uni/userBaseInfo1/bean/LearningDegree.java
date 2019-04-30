/**
 * Author: caiguangqian 8:27 2019/4/25
 * @param 
 * @return 
 * @apiNote:
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 学历
 */
public class LearningDegree {
    private Long id;//学历表的ID

    private Long userId;//用户ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;//开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间

    private Long countryId;//国家ID

    private Long cityId;//城市ID

    private Long schoolId;//学校ID

    private Long academicId;//academic表的ID

    private Long degreeId;//academic_degree表id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;//本记录的创建时间

    private Long byWho;//本记录的写入者

    private Boolean deleted;//本记录是否有效 0:有效 1:无效
    //学历表的ID的get方法
    public Long getId() {
        return id;
    }
    //学历表的ID的set方法
    public void setId(Long id) {
        this.id = id;
    }
    //用户ID的get方法
    public Long getUserId() {
        return userId;
    }
    //用户ID的set方法
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //开始时间的get方法
    public Date getBeginTime() {
        return beginTime;
    }
    //开始时间的set方法
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    //结束时间的get方法
    public Date getEndTime() {
        return endTime;
    }
    //结束时间的set方法
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    //国家ID的get方法
    public Long getCountryId() {
        return countryId;
    }
    //国家ID的set方法
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
    //城市ID的get方法
    public Long getCityId() {
        return cityId;
    }
    //城市ID的set方法
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    //学校ID的get方法
    public Long getSchoolId() {
        return schoolId;
    }
    //学校ID的set方法
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
    //academic表的ID的get方法
    public Long getAcademicId() {
        return academicId;
    }
    //academic表的ID的set方法
    public void setAcademicId(Long academicId) {
        this.academicId = academicId;
    }
    //academic_degree表id的get方法
    public Long getDegreeId() {
        return degreeId;
    }
    //academic_degree表id的set方法
    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }
    //本记录的创建时间的get方法
    public Date getDatetime() {
        return datetime;
    }
    //本记录的创建时间的set方法
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    //本记录的写入者的get方法
    public Long getByWho() {
        return byWho;
    }
    //本记录的写入者的set方法
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }
    //本记录是否有效的get方法
    public Boolean getDeleted() {
        return deleted;
    }
    //本记录是否有效的set方法
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

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

    public LearningDegree(Long id, Long userId, Date beginTime, Date endTime, Long countryId, Long cityId, Long schoolId, Long academicId, Long degreeId, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.countryId = countryId;
        this.cityId = cityId;
        this.schoolId = schoolId;
        this.academicId = academicId;
        this.degreeId = degreeId;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    public LearningDegree() {
    }
}