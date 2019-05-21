package edu.uni.educateAffair.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 22:25 2019/5/17
 */
public class CurriculumWithCondition {
    private Long id;

    private Long universityId;
    //校历ID
    private Long canlendarId;
    //作息表ID
    private Long timeTableId;
    //场地ID
    private Long fieldId;
    //班级ID
    private Long classId;
    //教师ID
    private Long employeeId;
    //课程ID
    private Long courseId;
    //输出校历时间
    @JsonProperty("Canlendar")
    private boolean Canlendar;
    //输出作息表名称
    @JsonProperty("Timetable")
    private boolean Timetable;
    //输出场地名称
    @JsonProperty("Field")
    private boolean Field;
    //输出班级名称
    @JsonProperty("Class")
    private boolean Class;
    //输出教师名称
    @JsonProperty("Employee")
    private boolean Employee;
    //输出课程名称
    @JsonProperty("Course")
    private boolean Course;

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

    public boolean isCanlendar() {
        return Canlendar;
    }

    public void setCanlendar(boolean canlendar) {
        Canlendar = canlendar;
    }

    public boolean isTimetable() {
        return Timetable;
    }

    public void setTimetable(boolean timetable) {
        Timetable = timetable;
    }

    public boolean isField() {
        return Field;
    }

    public void setField(boolean field) {
        Field = field;
    }

    public boolean isClass() {
        return Class;
    }

    public void setClass(boolean aClass) {
        Class = aClass;
    }

    public boolean isEmployee() {
        return Employee;
    }

    public void setEmployee(boolean employee) {
        Employee = employee;
    }

    public boolean isCourse() {
        return Course;
    }

    public void setCourse(boolean course) {
        Course = course;
    }

    @Override
    public String toString() {
        return "CurriculumWithCondition{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", canlendarId=" + canlendarId +
                ", timeTableId=" + timeTableId +
                ", fieldId=" + fieldId +
                ", classId=" + classId +
                ", employeeId=" + employeeId +
                ", courseId=" + courseId +
                ", Canlendar=" + Canlendar +
                ", Timetable=" + Timetable +
                ", Field=" + Field +
                ", Class=" + Class +
                ", Employee=" + Employee +
                ", Course=" + Course +
                '}';
    }
}
