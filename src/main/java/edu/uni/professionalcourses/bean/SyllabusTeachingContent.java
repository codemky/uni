package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SyllabusTeachingContent {
    private Long id;

    private Long universityId;

    private Long courseSyllabusDescriptionId;

    private String teachGoalRequire;

    private String teachImportantPoint;

    private String teachDifficultPoint;

    private String teachMethod;

    private String chapterContent;

    private String reflectionQuestion;

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

    public Long getCourseSyllabusDescriptionId() {
        return courseSyllabusDescriptionId;
    }

    public void setCourseSyllabusDescriptionId(Long courseSyllabusDescriptionId) {
        this.courseSyllabusDescriptionId = courseSyllabusDescriptionId;
    }

    public String getTeachGoalRequire() {
        return teachGoalRequire;
    }

    public void setTeachGoalRequire(String teachGoalRequire) {
        this.teachGoalRequire = teachGoalRequire == null ? null : teachGoalRequire.trim();
    }

    public String getTeachImportantPoint() {
        return teachImportantPoint;
    }

    public void setTeachImportantPoint(String teachImportantPoint) {
        this.teachImportantPoint = teachImportantPoint == null ? null : teachImportantPoint.trim();
    }

    public String getTeachDifficultPoint() {
        return teachDifficultPoint;
    }

    public void setTeachDifficultPoint(String teachDifficultPoint) {
        this.teachDifficultPoint = teachDifficultPoint == null ? null : teachDifficultPoint.trim();
    }

    public String getTeachMethod() {
        return teachMethod;
    }

    public void setTeachMethod(String teachMethod) {
        this.teachMethod = teachMethod == null ? null : teachMethod.trim();
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent == null ? null : chapterContent.trim();
    }

    public String getReflectionQuestion() {
        return reflectionQuestion;
    }

    public void setReflectionQuestion(String reflectionQuestion) {
        this.reflectionQuestion = reflectionQuestion == null ? null : reflectionQuestion.trim();
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