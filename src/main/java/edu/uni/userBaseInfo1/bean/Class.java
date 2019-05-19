package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class Class {
    private Long id;

    private Long universityId;

    private Long departmentId;

    private String name;

    private String ename;

    private Long specialtyId;

    private String code;

    private Integer cyear;

    private Integer cmonth;

    private Integer clength;

    private Boolean cover;

    private Long headteacher;

    private Date datetime;

    private Long byWho;

    private Boolean deleted;

    public Class() {
    }

    public Class(Long id, Long universityId, Long departmentId, String name, String ename, Long specialtyId, String code, Integer cyear, Integer cmonth, Integer clength, Boolean cover, Long headteacher, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.universityId = universityId;
        this.departmentId = departmentId;
        this.name = name;
        this.ename = ename;
        this.specialtyId = specialtyId;
        this.code = code;
        this.cyear = cyear;
        this.cmonth = cmonth;
        this.clength = clength;
        this.cover = cover;
        this.headteacher = headteacher;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCyear() {
        return cyear;
    }

    public void setCyear(Integer cyear) {
        this.cyear = cyear;
    }

    public Integer getCmonth() {
        return cmonth;
    }

    public void setCmonth(Integer cmonth) {
        this.cmonth = cmonth;
    }

    public Integer getClength() {
        return clength;
    }

    public void setClength(Integer clength) {
        this.clength = clength;
    }

    public Boolean getCover() {
        return cover;
    }

    public void setCover(Boolean cover) {
        this.cover = cover;
    }

    public Long getHeadteacher() {
        return headteacher;
    }

    public void setHeadteacher(Long headteacher) {
        this.headteacher = headteacher;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", ename='" + ename + '\'' +
                ", specialtyId=" + specialtyId +
                ", code='" + code + '\'' +
                ", cyear=" + cyear +
                ", cmonth=" + cmonth +
                ", clength=" + clength +
                ", cover=" + cover +
                ", headteacher=" + headteacher +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}