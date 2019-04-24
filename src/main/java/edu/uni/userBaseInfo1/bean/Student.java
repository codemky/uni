package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class Student {
    private Long id;

    private String stuNo;

    private Date beginLearnDate;

    private String grade;

    private Long majorId;

    private Long classId;

    private Long politicalId;

    private Long liveRoom;

    private Long homeAddress;

    private Long mailAddress;

    private Date datetime;

    private Long byWho;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
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
        this.grade = grade == null ? null : grade.trim();
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
}