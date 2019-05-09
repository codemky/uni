/*
author:  zhangzhanqiao
create:  2019.4.24
modified:  2019.4.24
description：学生主要信息
*/
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/*
student实体类 学生主要信息
（如果有需要说明，可以放在这里）
*/
public class Student {
    // Student数据表唯一编号ID
    private Long id;
    //用户表id
    private Long userId;
    //学校id
    private Long universityId;
    // 学号
    private String stuNo;
    // 入学日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginLearnDate;
    // 当前年级
    private String grade;
    // 当前主修专业
    private Long majorId;
    // 当前班级。专业班，行政班。除选修课外的大部分成绩单，都在本班输入
    private Long classId;
    // 关联到政治面貌id
    private Long politicalId;
    // 当前宿舍 field.id
    private Long liveRoom;
    // 当前住址 关联address.id
    private Long homeAddress;
    // 当前通信地址 关联address.id
    private Long mailAddress;
    // 本记录的创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
    // 本记录的写入者
    private Long byWho;
    // 本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public Date getBeginLearnDate() {
        return beginLearnDate;
    }

    public void setBeginLearnDate(Date beginLearnDate) {
        this.beginLearnDate = beginLearnDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getPoliticalId() {
        return politicalId;
    }

    public void setPoliticalId(Long politicalId) {
        this.politicalId = politicalId;
    }

    public Long getLiveRoom() {
        return liveRoom;
    }

    public void setLiveRoom(Long liveRoom) {
        this.liveRoom = liveRoom;
    }

    public Long getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Long homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Long getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(Long mailAddress) {
        this.mailAddress = mailAddress;
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
        return "Student{" +
                "id=" + id +
                ", userId=" + userId +
                ", universityId=" + universityId +
                ", stuNo='" + stuNo + '\'' +
                ", beginLearnDate=" + beginLearnDate +
                ", grade='" + grade + '\'' +
                ", majorId=" + majorId +
                ", classId=" + classId +
                ", politicalId=" + politicalId +
                ", liveRoom=" + liveRoom +
                ", homeAddress=" + homeAddress +
                ", mailAddress=" + mailAddress +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public Student(Long id, Long userId, Long universityId, String stuNo, Date beginLearnDate, String grade, Long majorId, Long classId, Long politicalId, Long liveRoom, Long homeAddress, Long mailAddress, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.universityId = universityId;
        this.stuNo = stuNo;
        this.beginLearnDate = beginLearnDate;
        this.grade = grade;
        this.majorId = majorId;
        this.classId = classId;
        this.politicalId = politicalId;
        this.liveRoom = liveRoom;
        this.homeAddress = homeAddress;
        this.mailAddress = mailAddress;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }
}