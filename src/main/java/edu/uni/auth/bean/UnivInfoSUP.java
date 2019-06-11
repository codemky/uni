package edu.uni.auth.bean;

/**
 * @author 何亮
 */
public class UnivInfoSUP {
    private Long universityId;
    private String universityName;
    private String universityEname;
    private String universityTelephone;

    private String userName;
    private String userIdentification;
    private Integer userSex;

    private Integer status;

    public UnivInfoSUP() {
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityEname() {
        return universityEname;
    }

    public void setUniversityEname(String universityEname) {
        this.universityEname = universityEname;
    }

    public String getUniversityTelephone() {
        return universityTelephone;
    }

    public void setUniversityTelephone(String universityTelephone) {
        this.universityTelephone = universityTelephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdentification() {
        return userIdentification;
    }

    public void setUserIdentification(String userIdentification) {
        this.userIdentification = userIdentification;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UnivInfoSUP(String universityName, String universityEname, String universityTelephone, String userName, String userIdentification, Integer userSex, Integer status) {
        this.universityName = universityName;
        this.universityEname = universityEname;
        this.universityTelephone = universityTelephone;
        this.userName = userName;
        this.userIdentification = userIdentification;
        this.userSex = userSex;
        this.status = status;
    }
}
