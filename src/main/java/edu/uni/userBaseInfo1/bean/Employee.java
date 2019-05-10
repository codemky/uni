package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 职员主要信息
 */
public class Employee {
    //职员表唯一id
    private Long id;

    //用户表id
    private Long userId;

    //学校id
    private Long universityId;

    //员工编号
    private String empNo;

    //当前所属学院
    private Long departmentId;

    //当前所在科室
    private Long subdepartmentId;

    //雇员简历表id
    private Long employHistoryId;

    //主修专业 关联到二级学科表id
    private Long disciplineId;

    //政治面貌
    private Long politicalId;

    //当前行政岗位
    private Long positionId;

    //当前住址
    private Long homeAddressId;

    ////当前通信地址
    private Long mailAddressId;

    //本记录的创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userId=" + userId +
                ", universityId=" + universityId +
                ", empNo='" + empNo + '\'' +
                ", departmentId=" + departmentId +
                ", subdepartmentId=" + subdepartmentId +
                ", employHistoryId=" + employHistoryId +
                ", disciplineId=" + disciplineId +
                ", politicalId=" + politicalId +
                ", positionId=" + positionId +
                ", homeAddressId=" + homeAddressId +
                ", mailAddressId=" + mailAddressId +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
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

    public Employee(Long id, Long userId, Long universityId, String empNo, Long departmentId, Long subdepartmentId, Long employHistoryId, Long disciplineId, Long politicalId, Long positionId, Long homeAddressId, Long mailAddressId, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.universityId = universityId;
        this.empNo = empNo;
        this.departmentId = departmentId;
        this.subdepartmentId = subdepartmentId;
        this.employHistoryId = employHistoryId;
        this.disciplineId = disciplineId;
        this.politicalId = politicalId;
        this.positionId = positionId;
        this.homeAddressId = homeAddressId;
        this.mailAddressId = mailAddressId;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    public Employee() {
    }
}