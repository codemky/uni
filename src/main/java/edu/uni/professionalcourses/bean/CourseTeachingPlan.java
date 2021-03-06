package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseTeachingPlan {
    private Long id;

    private Long universityId;

    private Long courseId;

    private Long courseTeachingScheduleId;

    private Long courseExperimentScheduleId;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseTeachingScheduleId() {
        return courseTeachingScheduleId;
    }

    public void setCourseTeachingScheduleId(Long courseTeachingScheduleId) {
        this.courseTeachingScheduleId = courseTeachingScheduleId;
    }

    public Long getCourseExperimentScheduleId() {
        return courseExperimentScheduleId;
    }

    public void setCourseExperimentScheduleId(Long courseExperimentScheduleId) {
        this.courseExperimentScheduleId = courseExperimentScheduleId;
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