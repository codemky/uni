package edu.uni.place.bean;

import java.util.Date;

public class Fieldapplyapproval {
    private Long id;

    private Long universityId;

    private Long fieldApplyId;

    private Long employeeId;

    private Integer result;

    private String content;

    private Long preApprovalId;

    private Date datetime;

    private Long byWho;

    private Integer deleted;

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

    public Long getFieldApplyId() {
        return fieldApplyId;
    }

    public void setFieldApplyId(Long fieldApplyId) {
        this.fieldApplyId = fieldApplyId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getPreApprovalId() {
        return preApprovalId;
    }

    public void setPreApprovalId(Long preApprovalId) {
        this.preApprovalId = preApprovalId;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}