package edu.uni.userBaseInfo1.bean;

/**
 * 学位表
 */
public class AcademicDegree {
    //学位表id
    private Long id;

    //具体内容
    private String degreeInfo;

    //学位表id的get方法
    public Long getId() {
        return id;
    }

    //学位表id的set方法
    public void setId(Long id) {
        this.id = id;
    }

    //具体内容的get方法
    public String getDegreeInfo() {
        return degreeInfo;
    }

    //具体内容的set方法
    public void setDegreeInfo(String degreeInfo) {
        this.degreeInfo = degreeInfo == null ? null : degreeInfo.trim();
    }
}