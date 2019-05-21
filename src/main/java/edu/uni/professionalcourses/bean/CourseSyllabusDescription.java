package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseSyllabusDescription {
    private Long id;

    private Long universityId;

    private Long courseSyllabusId;

    private String chapter;

    private String content;

    private Integer teachingHour;

    private String assessmentRequirement;

    private Long courseBookId;

    private String instruction;

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

    public Long getCourseSyllabusId() {
        return courseSyllabusId;
    }

    public void setCourseSyllabusId(Long courseSyllabusId) {
        this.courseSyllabusId = courseSyllabusId;
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

    public Integer getTeachingHour() {
        return teachingHour;
    }

    public void setTeachingHour(Integer teachingHour) {
        this.teachingHour = teachingHour;
    }

    public String getAssessmentRequirement() {
        return assessmentRequirement;
    }

    public void setAssessmentRequirement(String assessmentRequirement) {
        this.assessmentRequirement = assessmentRequirement == null ? null : assessmentRequirement.trim();
    }

    public Long getCourseBookId() {
        return courseBookId;
    }

    public void setCourseBookId(Long courseBookId) {
        this.courseBookId = courseBookId;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
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