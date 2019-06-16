package edu.uni.userBaseInfo1.bean;
/*
author:  laizhouhao
create:  2019.4.5
modified:  2019.4.24
description：用户的实体类
*/
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/*
用户实体类
 */
public class User {
    //数据表唯一编号id
    private Long id;

    //用户名
    private String userName;

    //用户身份证
    private String identification;

    //用户性别，0：女 1:男 2：不详 默认2
    private Integer userSex;

    //用户生日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String userBirthday;

    //用户学号、教工号、邮箱、手机号
    private String name;

    //用户密码
    private String pwd;

    //局部密钥
    private String salt;

    //学校id
    private Long universityId;

    //用户类型 0: 游客（已注册，但身份未确认） 1: 学生  2: 教职员工  3: 校外职员  4: 学生亲属
    private Integer userType;

    //用户状态(0：正常，1：锁定，2：作废
    private Integer status;

    //注册时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regist;

    //无参构造方法
    public User() {
    }

    //有参构造方法
    public User(Long id, String userName, String identification, Integer userSex, String userBirthday, String name, String pwd, String salt, Long universityId, Integer userType, Integer status, Date regist) {
        this.id = id;
        this.userName = userName;
        this.identification = identification;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.name = name;
        this.pwd = pwd;
        this.salt = salt;
        this.universityId = universityId;
        this.userType = userType;
        this.status = status;
        this.regist = regist;
    }

    //获取用户id
    public Long getId() {
        return id;
    }

    //写用户id
    public void setId(Long id) {
        this.id = id;
    }

    //获取用户名
    public String getUserName() {
        return userName;
    }

    //写用户名
    public void setUserName(String userName) {
        //用户名允许为空
        this.userName = userName == null ? null : userName.trim();
    }

    //获取用户身份证号码
    public String getIdentification() {
        return identification;
    }

    //写用户身份证号码
    public void setIdentification(String identification) {
        //身份证号码允许为空
        this.identification = identification == null ? null : identification.trim();
    }

    //获取用户性别
    public Integer getUserSex() {
        return userSex;
    }

    //设置用户性别
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    //获取用户生日
    public String getUserBirthday() {
        return userBirthday;
    }

    //写用户生日
    public void setUserBirthday(String userBirthday) {
        //用户生日可以为空
        this.userBirthday = userBirthday == null ? null : userBirthday.trim();
    }

    //获取学号、教工号、邮箱、手机号
    public String getName() {
        return name;
    }

    //写学号、教工号、邮箱、手机号
    public void setName(String name) {
        //学号、教工号、邮箱、手机号可以为空
        this.name = name == null ? null : name.trim();
    }

    //获取用户密码
    public String getPwd() {
        return pwd;
    }

    //写用户密码
    public void setPwd(String pwd) {
        //用户密码可以为空
        this.pwd = pwd == null ? null : pwd.trim();
    }

    //获取局部密钥
    public String getSalt() {
        return salt;
    }

    //写局部密钥
    public void setSalt(String salt) {
        //局部密钥可以为空
        this.salt = salt == null ? null : salt.trim();
    }

    //获取学校id
    public Long getUniversityId() {
        return universityId;
    }

    //写学校id
    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    //获取用户类型
    public Integer getUserType() {
        return userType;
    }

    //写用户类型
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    //获取用户状态
    public Integer getStatus() {
        return status;
    }

    //写用户状态
    public void setStatus(Integer status) {
        this.status = status;
    }

    //获取用户注册时间
    public Date getRegist() {
        return regist;
    }

    //写用户注册时间
    public void setRegist(Date regist) {
        this.regist = regist;
    }

    //用户的各项属性
    public java.lang.String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", identification='" + identification + '\'' +
                ", userSex=" + userSex +
                ", userBirthday='" + userBirthday + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                ", universityId=" + universityId +
                ", userType=" + userType +
                ", status=" + status +
                ", regist=" + regist +
                '}';
    }

//    public static boolean isValidForApply(User user){
//        return user.getId() != null && user.getUserName() != null &&
//                user.getIdentification() != null && user.getUserSex() != null &&
//                user.getName() != null && user.getPwd() != null &&
//                employee.getPoliticalId() != null ;
//    }
//
//    public static void copyPropertiesForApply(Employee new_employee, Employee old_employee){
//        new_employee.setUniversityId(old_employee.getUniversityId());
//        new_employee.setUserId(old_employee.getUserId());
//        new_employee.setHomeAddressId(old_employee.getHomeAddressId());
//        new_employee.setPhoneEcommId(old_employee.getPhoneEcommId());
//        new_employee.setDatetime(new Date());
//        new_employee.setDeleted(true);
//    }




}