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

    //联系方式
    private Long phoneEcommId;;

    //本记录的创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

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

    public Long getPhoneEcommId() {
        return phoneEcommId;
    }

    public void setPhoneEcommId(Long phoneEcommId) {
        this.phoneEcommId = phoneEcommId;
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
                ", phoneEcommId=" + phoneEcommId +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public static boolean isValidForApply(Employee employee){
        return employee.getId() != null && employee.getEmpNo() != null &&
                employee.getDepartmentId() != null && employee.getSubdepartmentId() != null &&
                employee.getEmployHistoryId() != null && employee.getDisciplineId() != null &&
                employee.getPoliticalId() != null ;
    }

    public static void copyPropertiesForApply(Employee new_employee, Employee old_employee){
        new_employee.setUniversityId(old_employee.getUniversityId());
        new_employee.setUserId(old_employee.getUserId());
        new_employee.setHomeAddressId(old_employee.getHomeAddressId());
        new_employee.setPhoneEcommId(old_employee.getPhoneEcommId());
        new_employee.setDatetime(new Date());
        new_employee.setDeleted(true);
    }


    public Employee() {
    }

    public Employee(Long id, Long userId, Long universityId, String empNo, Long departmentId, Long subdepartmentId, Long employHistoryId, Long disciplineId, Long politicalId, Long positionId, Long homeAddressId, Long phoneEcommId, Date datetime, Long byWho, Boolean deleted) {
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
        this.phoneEcommId = phoneEcommId;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }
}