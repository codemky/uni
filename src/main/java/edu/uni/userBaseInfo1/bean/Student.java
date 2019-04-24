/*
author:  张展侨
create:  2019.4.24
modified:  2019.4.24
description：学生主要信息
*/
package edu.uni.userBaseInfo1.bean;

import java.util.Date;
/*
picture实体类 学生主要信息
（如果有需要说明，可以放在这里）
*/
public class Student {
    // Student数据表唯一编号ID
    private Long id;
    // 学号
    private String stuNo;
    // 入学日期
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
    private Date datetime;
    // 本记录的写入者
    private Long byWho;
    // 本记录是否有效 0:有效 1:无效
    private Boolean deleted;
    //获取学号
    public Long getId() {
        return id;
    }
    //设置学号
    public void setId(Long id) {
        this.id = id;
    }
    //获取入学日期
    public String getStuNo() {
        return stuNo;
    }
    //设置入学日期
    public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
    }
    //获取当前年级
    public Date getBeginLearnDate() {
        return beginLearnDate;
    }
    //设置当前年级
    public void setBeginLearnDate(Date beginLearnDate) {
        this.beginLearnDate = beginLearnDate;
    }
    //获取当前主修专业
    public String getGrade() {
        return grade;
    }
    //设置当前主修专业
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }
    //获取当前班级
    public Long getMajorId() {
        return majorId;
    }
    //设置当前班级
    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }
    //获取关联到政治面貌id
    public Long getClassId() {
        return classId;
    }
    //设置关联到政治面貌id
    public void setClassId(Long classId) {
        this.classId = classId;
    }
    //获取当前宿舍 field.id
    public Long getPoliticalId() {
        return politicalId;
    }
    //设置当前宿舍 field.id
    public void setPoliticalId(Long politicalId) {
        this.politicalId = politicalId;
    }
    //获取当前住址 关联address.id
    public Long getLiveRoom() {
        return liveRoom;
    }
    //设置当前住址 关联address.id
    public void setLiveRoom(Long liveRoom) {
        this.liveRoom = liveRoom;
    }
    //获取当前住址 关联address.id
    public Long getHomeAddress() {
        return homeAddress;
    }
    //设置当前住址 关联address.id
    public void setHomeAddress(Long homeAddress) {
        this.homeAddress = homeAddress;
    }
    //获取当前通信地址 关联address.id
    public Long getMailAddress() {
        return mailAddress;
    }
    //设置当前通信地址 关联address.id
    public void setMailAddress(Long mailAddress) {
        this.mailAddress = mailAddress;
    }
    //获取本记录创建时间
    public Date getDatetime() {
        return datetime;
    }
    //设置本记录创建时间
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    //获取本记录的写入者
    public Long getByWho() {
        return byWho;
    }
    //设置本记录的写入者
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }
    //获取本记录是否有效
    public Boolean getDeleted() {
        return deleted;
    }
    //设置本记录是否有效
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    //本实体类的toString()方法
    @java.lang.Override
    public java.lang.String toString() {
        return "Student{" +
                "id=" + id +
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
}