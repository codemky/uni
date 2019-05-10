package edu.uni.administrativestructure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * author : 黄永佳
 * create : 2019/4/26 0023 15:34
 * modified :
 * 功能 :
 **/
public class ClassmatePosition {
    // 表主键
    private Long id;
    //所属学校id
    private Long universityId;

    //班级人员id
    private Long classmateId;

    //    岗位id
    private Long positionId;

    //    数据创建时间
    private Date datetime;

    //    该记录创建人
    private Long byWho;

    //    数据是否有效的标志
    private Boolean deleted;
    /*
     * 获取与写方法
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

    public Long getClassmateId() {
        return classmateId;
    }

    public void setClassmateId(Long classmateId) {
        this.classmateId = classmateId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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