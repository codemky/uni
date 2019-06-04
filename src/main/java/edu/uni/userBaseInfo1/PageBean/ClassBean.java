package edu.uni.userBaseInfo1.PageBean;

/**
 * @Author chenenru
 * @ClassName ClassBean
 * @Description
 * @Date 2019/6/4 15:35
 * @Version 1.0
 **/
public class ClassBean {
    private Long classId;
    private String code;
    private String name;
    private Integer number;
    private String headteacher;
    private String moniter;

    public ClassBean() {
    }

    public ClassBean(Long classId, String code, String name, Integer number, String headteacher, String moniter) {
        this.classId = classId;
        this.code = code;
        this.name = name;
        this.number = number;
        this.headteacher = headteacher;
        this.moniter = moniter;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHeadteacher() {
        return headteacher;
    }

    public void setHeadteacher(String headteacher) {
        this.headteacher = headteacher;
    }

    public String getMoniter() {
        return moniter;
    }

    public void setMoniter(String moniter) {
        this.moniter = moniter;
    }

    @Override
    public String toString() {
        return "ClassBean{" +
                "classId=" + classId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", headteacher='" + headteacher + '\'' +
                ", moniter='" + moniter + '\'' +
                '}';
    }
}
