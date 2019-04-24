package edu.uni.userBaseInfo1.bean;

public class AcademicDegree {
    private Long id;

    private String degreeInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegreeInfo() {
        return degreeInfo;
    }

    public void setDegreeInfo(String degreeInfo) {
        this.degreeInfo = degreeInfo == null ? null : degreeInfo.trim();
    }
}