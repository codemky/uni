package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseExperimentDescription {
    private Long id;

    private Long universityId;

    private Long courseExperimentId;

    private String mainEquipment;

    private String methodAndRequirements;

    private String assessmentAndReport;

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

    public Long getCourseExperimentId() {
        return courseExperimentId;
    }

    public void setCourseExperimentId(Long courseExperimentId) {
        this.courseExperimentId = courseExperimentId;
    }

    public String getMainEquipment() {
        return mainEquipment;
    }

    public void setMainEquipment(String mainEquipment) {
        this.mainEquipment = mainEquipment == null ? null : mainEquipment.trim();
    }

    public String getMethodAndRequirements() {
        return methodAndRequirements;
    }

    public void setMethodAndRequirements(String methodAndRequirements) {
        this.methodAndRequirements = methodAndRequirements == null ? null : methodAndRequirements.trim();
    }

    public String getAssessmentAndReport() {
        return assessmentAndReport;
    }

    public void setAssessmentAndReport(String assessmentAndReport) {
        this.assessmentAndReport = assessmentAndReport == null ? null : assessmentAndReport.trim();
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