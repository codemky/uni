package edu.uni.userBaseInfo1.bean;

/**
 * 受教育程度
 */
public class Academic {

    //教育程度表id
    private Long id;
    //具体内容
    private String academicInfo;

    //教育程度表id的get方法
    public Long getId() {
        return id;
    }
    //教育程度表id的set方法
    public void setId(Long id) {
        this.id = id;
    }
    //具体内容的get方法
    public String getAcademicInfo() {
        return academicInfo;
    }
    //具体内容的set方法
    public void setAcademicInfo(String academicInfo) {
        this.academicInfo = academicInfo == null ? null : academicInfo.trim();
    }

    @Override
    public String toString() {
        return "Academic{" +
                "id=" + id +
                ", academicInfo='" + academicInfo + '\'' +
                '}';
    }

    public Academic(Long id, String academicInfo) {
        this.id = id;
        this.academicInfo = academicInfo;
    }

    public Academic() {
    }
}