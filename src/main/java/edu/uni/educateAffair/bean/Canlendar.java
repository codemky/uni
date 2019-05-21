package edu.uni.educateAffair.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Canlendar {
    private Long id;

    private Long universityId;

    private Long semesterId;

    private Date theDate;

    private String week;

    private String day;

    private Integer holiday;

    private String describe;

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

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getTheDate() {
        return theDate;
    }

    public void setTheDate(Date theDate) {
        this.theDate = theDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public Integer getHoliday() {
        return holiday;
    }

    public void setHoliday(Integer holiday) {
        this.holiday = holiday;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
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
        return "Canlendar{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", semesterId=" + semesterId +
                ", theDate=" + theDate +
                ", week='" + week + '\'' +
                ", day='" + day + '\'' +
                ", holiday=" + holiday +
                ", describe='" + describe + '\'' +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}