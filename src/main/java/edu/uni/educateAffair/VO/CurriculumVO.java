package edu.uni.educateAffair.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 21:25 2019/5/11
 */
@Component
public class CurriculumVO {

        private Long id;

        private Long universityId;

        private Long canlendarId;

        private Long timeTableId;

        private Long fieldId;

        private Long classId;

        private Long employeeId;

        private Long courseId;

        private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
        private Date canlendarDate;

        private String timeTableName;

        private String fieldName;

        private String className;

        private String employeeName;

        private String courseName;

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

    public Long getCanlendarId() {
        return canlendarId;
    }

    public void setCanlendarId(Long canlendarId) {
        this.canlendarId = canlendarId;
    }

    public Long getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(Long timeTableId) {
        this.timeTableId = timeTableId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCanlendarDate() {
        return canlendarDate;
    }

    public void setCanlendarDate(Date canlendarDate) {
        this.canlendarDate = canlendarDate;
    }

    public String getTimeTableName() {
        return timeTableName;
    }

    public void setTimeTableName(String timeTableName) {
        this.timeTableName = timeTableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "CurriculumVO{" +
                " id=" + id +
                ", canlendarDate=" + canlendarDate +
                ", timeTableName='" + timeTableName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", className='" + className + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
