package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class Employee {
    private Long id;

    private String empNo;

    private Long departmentId;

    private Long subdepartmentId;

    private Long employHistoryId;

    private Long disciplineId;

    private Long politicalId;

    private Long positionId;

    private Long homeAddressId;

    private Long mailAddressId;

    private Date datetime;

    private Long byWho;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSubdepartmentId() {
        return subdepartmentId;
    }

    public void setSubdepartmentId(Long subdepartmentId) {
        this.subdepartmentId = subdepartmentId;
    }

    public Long getEmployHistoryId() {
        return employHistoryId;
    }

    public void setEmployHistoryId(Long employHistoryId) {
        this.employHistoryId = employHistoryId;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Long getPoliticalId() {
        return politicalId;
    }

    public void setPoliticalId(Long politicalId) {
        this.politicalId = politicalId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getHomeAddressId() {
        return homeAddressId;
    }

    public void setHomeAddressId(Long homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    public Long getMailAddressId() {
        return mailAddressId;
    }

    public void setMailAddressId(Long mailAddressId) {
        this.mailAddressId = mailAddressId;
    }

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