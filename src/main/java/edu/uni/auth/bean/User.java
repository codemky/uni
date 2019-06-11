package edu.uni.auth.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class User {
    public static final int Status_Common = 0;
    public static final int Status_Locked = 1;
    public static final int Status_Scrap = 2;

    private Long id;

    private String name;
    @JsonIgnore
    private String pwd;
    @JsonIgnore
    private Integer status;
    @JsonIgnore
    private String salt;

    private Long universityId;
    private University university;

    private Integer userType;

    private String userName;

    private String identification;

    private Integer userSex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date userBirthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regist;

    //用户的角色集合
    @JsonIgnore
    private List<String> roleStrlist;
    //用户的权限集合
    @JsonIgnore
    private List<String> perminsStrlist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification == null ? null : identification.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Date getRegist() {
        return regist;
    }

    public void setRegist(Date regist) {
        this.regist = regist;
    }

    public List<String> getRoleStrlist() {
        return roleStrlist;
    }

    public void setRoleStrlist(List<String> roleStrlist) {
        this.roleStrlist = roleStrlist;
    }

    public List<String> getPerminsStrlist() {
        return perminsStrlist;
    }

    public void setPerminsStrlist(List<String> perminsStrlist) {
        this.perminsStrlist = perminsStrlist;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}