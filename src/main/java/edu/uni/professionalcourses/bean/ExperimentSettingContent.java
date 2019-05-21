package edu.uni.professionalcourses.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExperimentSettingContent {
    private Long id;

    private Long universityId;

    private Long courseExperimentDescriptionId;

    private String name;

    private String content;

    private Integer hour;

    private Integer groupNumber;

    private String type;

    private String experimentCategory;

    private String establishRequirement;

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

    public Long getCourseExperimentDescriptionId() {
        return courseExperimentDescriptionId;
    }

    public void setCourseExperimentDescriptionId(Long courseExperimentDescriptionId) {
        this.courseExperimentDescriptionId = courseExperimentDescriptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getExperimentCategory() {
        return experimentCategory;
    }

    public void setExperimentCategory(String experimentCategory) {
        this.experimentCategory = experimentCategory == null ? null : experimentCategory.trim();
    }

    public String getEstablishRequirement() {
        return establishRequirement;
    }

    public void setEstablishRequirement(String establishRequirement) {
        this.establishRequirement = establishRequirement == null ? null : establishRequirement.trim();
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