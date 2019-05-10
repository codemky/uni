package edu.uni.administrativestructure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * author : 黄永佳
 * create : 2019/4/23 0023 15:34
 * modified :
 * 功能 :一级二级部门关系表的实体类
 **/
public class DepartmentSubdepartment {

    //   数据表的唯一id
    private Long id;

    //   二级部门所属学校id
    private Long universityId;

    //   二级部门所属一级部门id
    private Long departmentId;

    //    二级部门id
    private Long subdepartmentId;

    //    记录创建时间
    private Date datetime;

    //    记录创建人
    private Long byWho;

    //    记录有效标志
    private Boolean deleted;
    /*
     * getter和setter方法
     * */
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

    public Long getSubdepartmentId() {
        return subdepartmentId;
    }

    public void setSubdepartmentId(Long subdepartmentId) {
        this.subdepartmentId = subdepartmentId;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
}