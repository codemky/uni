package edu.uni.administrativestructure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * author : 黄永佳
 * create : 2019/4/19 0023 15:34
 * modified :
 * 功能 :一级部门表的实体类
 **/
public class Department {

    //数据表的唯一id
    private Long id;

    //所属学校id
    private Long universityId;

    //一级部门中文名
    private String name;

    //一级部门英文名
    private String ename;

    //部门办公室电话号码
    private String telephone;

    //当前部门负责人
    private Long head;

    //当前部门办公室
    private Long officeRoom;

    //当前学校联系领导
    private Long universityLeader;

    //记录创建时间
    private Date datetime;

    //记录创建人
    private Long byWho;

    //记录有效标志
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Long getHead() {
        return head;
    }

    public void setHead(Long head) {
        this.head = head;
    }

    public Long getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(Long officeRoom) {
        this.officeRoom = officeRoom;
    }

    public Long getUniversityLeader() {
        return universityLeader;
    }

    public void setUniversityLeader(Long universityLeader) {
        this.universityLeader = universityLeader;
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