package edu.uni.userBaseInfo1.PageBean;

import java.util.Date;

/**
 * @Author chenenru
 * @ClassName ClassmateBean
 * @Description
 * @Date 2019/6/4 15:44
 * @Version 1.0
 **/
public class ClassmateBean {
    private Long userId;
    private Long studentId;
    private String studentNo;
    private String studentName;
    private Date beginLearnDate;
    private String specialty;
    private String grade;
    private String sex;
    private String phone;
    private String political;
    private String position;
    private String className;

    public ClassmateBean() {
    }

    public ClassmateBean(Long userId, Long studentId, String studentNo, String studentName, Date beginLearnDate, String specialty, String grade, String sex, String phone, String political, String position, String className) {
        this.userId = userId;
        this.studentId = studentId;
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.beginLearnDate = beginLearnDate;
        this.specialty = specialty;
        this.grade = grade;
        this.sex = sex;
        this.phone = phone;
        this.political = political;
        this.position = position;
        this.className = className;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStudentId() {
        return studentId;
    }


    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBeginLearnDate() {
        return beginLearnDate;
    }

    public void setBeginLearnDate(Date beginLearnDate) {
        this.beginLearnDate = beginLearnDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassmateBean{" +
                "userId=" + userId +
                ", studentId=" + studentId +
                ", studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", beginLearnDate=" + beginLearnDate +
                ", specialty='" + specialty + '\'' +
                ", grade='" + grade + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", political='" + political + '\'' +
                ", position='" + position + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
