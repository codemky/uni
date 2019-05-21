package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TrainingProgram {
    private Long id;

    private Long universityId;

    private Long specialtyId;

    private String trainingTarget;

    private String trainingSpecifications;

    private Integer totalCredits;

    private Double gpa;

    private Integer compilsoryCredits;

    private Double compilsoryGpa;

    private Integer electiveCredits;

    private Double electiveGpa;

    private Integer practiceCredits;

    private Double practiceGpa;

    private Integer educateCredits;

    private Double educateGpa;

    private String version;

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

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getTrainingTarget() {
        return trainingTarget;
    }

    public void setTrainingTarget(String trainingTarget) {
        this.trainingTarget = trainingTarget == null ? null : trainingTarget.trim();
    }

    public String getTrainingSpecifications() {
        return trainingSpecifications;
    }

    public void setTrainingSpecifications(String trainingSpecifications) {
        this.trainingSpecifications = trainingSpecifications == null ? null : trainingSpecifications.trim();
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Integer getCompilsoryCredits() {
        return compilsoryCredits;
    }

    public void setCompilsoryCredits(Integer compilsoryCredits) {
        this.compilsoryCredits = compilsoryCredits;
    }

    public Double getCompilsoryGpa() {
        return compilsoryGpa;
    }

    public void setCompilsoryGpa(Double compilsoryGpa) {
        this.compilsoryGpa = compilsoryGpa;
    }

    public Integer getElectiveCredits() {
        return electiveCredits;
    }

    public void setElectiveCredits(Integer electiveCredits) {
        this.electiveCredits = electiveCredits;
    }

    public Double getElectiveGpa() {
        return electiveGpa;
    }

    public void setElectiveGpa(Double electiveGpa) {
        this.electiveGpa = electiveGpa;
    }

    public Integer getPracticeCredits() {
        return practiceCredits;
    }

    public void setPracticeCredits(Integer practiceCredits) {
        this.practiceCredits = practiceCredits;
    }

    public Double getPracticeGpa() {
        return practiceGpa;
    }

    public void setPracticeGpa(Double practiceGpa) {
        this.practiceGpa = practiceGpa;
    }

    public Integer getEducateCredits() {
        return educateCredits;
    }

    public void setEducateCredits(Integer educateCredits) {
        this.educateCredits = educateCredits;
    }

    public Double getEducateGpa() {
        return educateGpa;
    }

    public void setEducateGpa(Double educateGpa) {
        this.educateGpa = educateGpa;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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