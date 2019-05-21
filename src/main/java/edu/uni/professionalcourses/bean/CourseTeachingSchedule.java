package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseTeachingSchedule {
    private Long id;

    private Long universityId;

    private String week;

    private String chapter;

    private String content;

    private Integer teachHour;

    private Integer experimentHour;

    private String homework;

    private String actualProgress;

    private String outOfPlanReason;

    private Date datetime;

    private Long byWho;

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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter == null ? null : chapter.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTeachHour() {
        return teachHour;
    }

    public void setTeachHour(Integer teachHour) {
        this.teachHour = teachHour;
    }

    public Integer getExperimentHour() {
        return experimentHour;
    }

    public void setExperimentHour(Integer experimentHour) {
        this.experimentHour = experimentHour;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework == null ? null : homework.trim();
    }

    public String getActualProgress() {
        return actualProgress;
    }

    public void setActualProgress(String actualProgress) {
        this.actualProgress = actualProgress == null ? null : actualProgress.trim();
    }

    public String getOutOfPlanReason() {
        return outOfPlanReason;
    }

    public void setOutOfPlanReason(String outOfPlanReason) {
        this.outOfPlanReason = outOfPlanReason == null ? null : outOfPlanReason.trim();
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