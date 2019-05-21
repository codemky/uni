/*
author:  邓凯丰
create:  2019.5.10
modified:  2019.5.11
*/
package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Course {
    private Long id;

    private Long universityId;

    private String number;

    private String name;

    private String ename;

    private Long speciesId;

    private Long categoryId;

    private Long classificationId;

    private Long examTypeId;

    private Long examModeId;

    private Integer hour;

    private Float credit;

    private Integer syllabusHour;

    private Integer experimentHour;

    private String introduction;

    private String teachGoal;

    private String teachRequire;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public Long getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(Long examTypeId) {
        this.examTypeId = examTypeId;
    }

    public Long getExamModeId() {
        return examModeId;
    }

    public void setExamModeId(Long examModeId) {
        this.examModeId = examModeId;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public Integer getSyllabusHour() {
        return syllabusHour;
    }

    public void setSyllabusHour(Integer syllabusHour) {
        this.syllabusHour = syllabusHour;
    }

    public Integer getExperimentHour() {
        return experimentHour;
    }

    public void setExperimentHour(Integer experimentHour) {
        this.experimentHour = experimentHour;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getTeachGoal() {
        return teachGoal;
    }

    public void setTeachGoal(String teachGoal) {
        this.teachGoal = teachGoal == null ? null : teachGoal.trim();
    }

    public String getTeachRequire() {
        return teachRequire;
    }

    public void setTeachRequire(String teachRequire) {
        this.teachRequire = teachRequire == null ? null : teachRequire.trim();
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