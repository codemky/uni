package edu.uni.userBaseInfo1.bean;

import java.util.Date;

/**
 * 职员主要信息
 */
public class Employee {
    //同user.id一致
    private Long id;

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
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    //同user.id一致的get方法
    public Long getId() {
        return id;
    }

    //同user.id一致的set方法
    public void setId(Long id) {
        this.id = id;
    }

    //员工编号的get方法
    public String getEmpNo() {
        return empNo;
    }

    //员工编号的set方法
    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    //当前所属学院的get方法
    public Long getDepartmentId() {
        return departmentId;
    }

    //当前所属学院的set方法
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    //当前所在科室的get方法
    public Long getSubdepartmentId() {
        return subdepartmentId;
    }

    //当前所在科室的set方法
    public void setSubdepartmentId(Long subdepartmentId) {
        this.subdepartmentId = subdepartmentId;
    }

    //雇员简历表id的get方法
    public Long getEmployHistoryId() {
        return employHistoryId;
    }

    //雇员简历表id的set方法
    public void setEmployHistoryId(Long employHistoryId) {
        this.employHistoryId = employHistoryId;
    }

    //主修专业 关联到二级学科表id的get方法
    public Long getDisciplineId() {
        return disciplineId;
    }

    //主修专业 关联到二级学科表id的set方法
    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    //政治面貌的get方法
    public Long getPoliticalId() {
        return politicalId;
    }

    //政治面貌的set方法
    public void setPoliticalId(Long politicalId) {
        this.politicalId = politicalId;
    }

    //当前行政岗位的get方法
    public Long getPositionId() {
        return positionId;
    }

    //当前行政岗位的set方法
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    //当前住址的get方法
    public Long getHomeAddressId() {
        return homeAddressId;
    }

    //当前住址的set方法
    public void setHomeAddressId(Long homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    //当前通信地址的get方法
    public Long getMailAddressId() {
        return mailAddressId;
    }

    //当前通信地址的set方法
    public void setMailAddressId(Long mailAddressId) {
        this.mailAddressId = mailAddressId;
    }

    //本记录的创建时间的get方法
    public Date getDatetime() {
        return datetime;
    }

    //本记录的创建时间的set方法
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    //本记录的写入者的get方法
    public Long getByWho() {
        return byWho;
    }

    //本记录的写入者的set方法
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    //本记录是否有效 0:有效 1:无效的get方法
    public Boolean getDeleted() {
        return deleted;
    }

    //本记录是否有效 0:有效 1:无效的set方法
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Employee(Long id, String empNo, Long departmentId, Long subdepartmentId, Long employHistoryId, Long disciplineId, Long politicalId, Long positionId, Long homeAddressId, Long mailAddressId, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
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
}