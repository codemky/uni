package edu.uni.userBaseInfo1.alibaba.easyexcel.test.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

/**
 * @Author chenenru
 * @ClassName EmployeeModel
 * @Description 职员的信息模板
 * @Date 2019/5/22 8:30
 * @Version 1.0
 **/
public class EmployeeModel extends BaseRowModel {

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
    @ExcelProperty(index = 3)
    private String userBirthday;

    @ExcelProperty(index = 4)
    //职员工号
    private String userNumber
            ;
    @ExcelProperty(index = 5)
    //职员密码
    private String userPassword;

    @ExcelProperty(index = 6)
    //职员的秘钥
    private String usersecretKey;

    //employee表

    @ExcelProperty(index = 7,format = "yyyy-mm-DD HH:mm:ss")
    //入职时间
    private Date beginLearnDate;

    @ExcelProperty(index = 8)
    //职员所属学院
    private String departmentName;

    @ExcelProperty(index = 9)
    //所属科室、系
    private String subdepartmentName;

    @ExcelProperty(index = 10)
    //职员主修专业
    private String majorName;

    @ExcelProperty(index = 11)
    //职员简历
    private String employeehistory;

    @ExcelProperty(index = 12)
    //职员政治面貌
    private String political;

    @ExcelProperty(index = 13)
    //职员当前行政岗位
    private String position;

    @ExcelProperty(index = 14)
    //职员住址
    private String homeAddress;

    //邮政编码
    @ExcelProperty(index = 15)
    private String zipCode;

    @ExcelProperty(index = 16)
    //职员通讯方式
    private String mailEcomm;

    public EmployeeModel() {
    }

    public EmployeeModel(String userName, String identification, String userSex, String userBirthday, String userNumber, String userPassword, String usersecretKey, Date beginLearnDate, String departmentName, String subdepartmentName, String employeehistory, String majorName, String political, String position, String homeAddress, String zipCode, String mailEcomm) {
        this.userName = userName;
        this.identification = identification;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userNumber = userNumber;
        this.userPassword = userPassword;
        this.usersecretKey = usersecretKey;
        this.beginLearnDate = beginLearnDate;
        this.departmentName = departmentName;
        this.subdepartmentName = subdepartmentName;
        this.employeehistory = employeehistory;
        this.majorName = majorName;
        this.political = political;
        this.position = position;
        this.homeAddress = homeAddress;
        this.zipCode = zipCode;
        this.mailEcomm = mailEcomm;
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

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
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

    public String getSubdepartmentName() {
        return subdepartmentName;
    }

    public void setSubdepartmentName(String subdepartmentName) {
        this.subdepartmentName = subdepartmentName;
    }

    public String getEmployeehistory() {
        return employeehistory;
    }

    public void setEmployeehistory(String employeehistory) {
        this.employeehistory = employeehistory;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMailEcomm() {
        return mailEcomm;
    }

    public void setMailEcomm(String mailEcomm) {
        this.mailEcomm = mailEcomm;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "userName='" + userName + '\'' +
                ", identification='" + identification + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                ", userNumber='" + userNumber + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", usersecretKey='" + usersecretKey + '\'' +
                ", beginLearnDate=" + beginLearnDate +
                ", departmentName='" + departmentName + '\'' +
                ", subdepartmentName='" + subdepartmentName + '\'' +
                ", employeehistory='" + employeehistory + '\'' +
                ", majorName='" + majorName + '\'' +
                ", political='" + political + '\'' +
                ", position='" + position + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mailEcomm='" + mailEcomm + '\'' +
                '}';
    }
}
