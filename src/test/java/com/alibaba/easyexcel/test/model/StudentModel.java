/*
author:  zhangzhanqiao
create:  2019.4.24
modified:  2019.4.24
description：学生主要信息
*/
package com.alibaba.easyexcel.test.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

/*
student实体类 学生主要信息
（如果有需要说明，可以放在这里）
*/
public class StudentModel extends BaseRowModel {

    //user表
    //用户名
    @ExcelProperty(index = 0)
    private String userName;

    //用户身份证
    @ExcelProperty(index = 1)
    private String identification;

    //用户性别，0：女 1:男 2：不详 默认2
    @ExcelProperty(index = 2)
    private String userSex;

    //用户生日
    @ExcelProperty(index = 3,format = "yyyy-MM-dd")
    private Date userBirthday;

    @ExcelProperty(index = 4)
    //学生学号
    private String userNumber
            ;
    @ExcelProperty(index = 5)
    //学生密码
    private String userPassword;

    @ExcelProperty(index = 6)
    //学生的秘钥
    private String usersecretKey;

    //student表
    //入学日期
    @ExcelProperty(index = 7,format = "yyyy-MM-dd")
    private Date beginLearnDate;

    //学院
    @ExcelProperty(index = 8)
    private String departmentName;

    // 当前年级
    @ExcelProperty(index = 9)
    private String gradeName;

    // 当前主修专业
    @ExcelProperty(index = 10)
    private String majorName;

    // 当前班级。专业班，行政班。除选修课外的大部分成绩单，都在本班输入
    @ExcelProperty(index = 11)
    private String className;

    //政治面貌
    @ExcelProperty(index = 12)
    private String political;

    // 当前宿舍
    @ExcelProperty(index = 13)
    private String liveRoom;

    // 当前住址
    @ExcelProperty(index = 14)
    private String homeAddress;

    // 当前通信方式
    @ExcelProperty(index = 15)
    private String mailEcomm;

    public StudentModel() {
    }

    public StudentModel(String userName, String identification, String userSex, Date userBirthday, String userNumber, String userPassword, String usersecretKey, Date beginLearnDate, String departmentName, String gradeName, String majorName, String className, String political, String liveRoom, String homeAddress, String mailEcomm) {
        this.userName = userName;
        this.identification = identification;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userNumber = userNumber;
        this.userPassword = userPassword;
        this.usersecretKey = usersecretKey;
        this.beginLearnDate = beginLearnDate;
        this.departmentName = departmentName;
        this.gradeName = gradeName;
        this.majorName = majorName;
        this.className = className;
        this.political = political;
        this.liveRoom = liveRoom;
        this.homeAddress = homeAddress;
        this.mailEcomm = mailEcomm;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "userName='" + userName + '\'' +
                ", identification='" + identification + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                ", userNumber='" + userNumber + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", usersecretKey='" + usersecretKey + '\'' +
                ", beginLearnDate=" + beginLearnDate +
                ", departmentName='" + departmentName + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", className='" + className + '\'' +
                ", political='" + political + '\'' +
                ", liveRoom='" + liveRoom + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", mailEcomm='" + mailEcomm + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUsersecretKey() {
        return usersecretKey;
    }

    public void setUsersecretKey(String usersecretKey) {
        this.usersecretKey = usersecretKey;
    }

    public Date getBeginLearnDate() {
        return beginLearnDate;
    }

    public void setBeginLearnDate(Date beginLearnDate) {
        this.beginLearnDate = beginLearnDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getLiveRoom() {
        return liveRoom;
    }

    public void setLiveRoom(String liveRoom) {
        this.liveRoom = liveRoom;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getMailEcomm() {
        return mailEcomm;
    }

    public void setMailEcomm(String mailEcomm) {
        this.mailEcomm = mailEcomm;
    }
}