package edu.uni.userBaseInfo1.bean;

public class Academic {
    private Long id;

    private String academicInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcademicInfo() {
        return academicInfo;
    }

    public void setAcademicInfo(String academicInfo) {
        this.academicInfo = academicInfo == null ? null : academicInfo.trim();
    }
}