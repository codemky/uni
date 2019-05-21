package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Specialty {
    private Long id;

    private Long universityId;

    private Long departmentId;

    private String number;

    private String name;

    private String ename;

    private Long disciplineCategoryId;

    private Long firstLevelDisciplineId;

    private Long secondLevelDisciplineId;

    private Long academicId;

    private String levelName;

    private Integer schoolingLength;

    private Integer minSchoolingLength;

    private Integer maxSchoolingLength;

    private Integer totalCredit;

    private String note;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public Long getDisciplineCategoryId() {
        return disciplineCategoryId;
    }

    public void setDisciplineCategoryId(Long disciplineCategoryId) {
        this.disciplineCategoryId = disciplineCategoryId;
    }

    public Long getFirstLevelDisciplineId() {
        return firstLevelDisciplineId;
    }

    public void setFirstLevelDisciplineId(Long firstLevelDisciplineId) {
        this.firstLevelDisciplineId = firstLevelDisciplineId;
    }

    public Long getSecondLevelDisciplineId() {
        return secondLevelDisciplineId;
    }

    public void setSecondLevelDisciplineId(Long secondLevelDisciplineId) {
        this.secondLevelDisciplineId = secondLevelDisciplineId;
    }

    public Long getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Long academicId) {
        this.academicId = academicId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Integer getSchoolingLength() {
        return schoolingLength;
    }

    public void setSchoolingLength(Integer schoolingLength) {
        this.schoolingLength = schoolingLength;
    }

    public Integer getMinSchoolingLength() {
        return minSchoolingLength;
    }

    public void setMinSchoolingLength(Integer minSchoolingLength) {
        this.minSchoolingLength = minSchoolingLength;
    }

    public Integer getMaxSchoolingLength() {
        return maxSchoolingLength;
    }

    public void setMaxSchoolingLength(Integer maxSchoolingLength) {
        this.maxSchoolingLength = maxSchoolingLength;
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Integer totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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