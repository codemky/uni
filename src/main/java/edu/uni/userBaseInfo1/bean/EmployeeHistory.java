/**
 * Author: caiguangqian 8:27 2019/4/25
 * @param 
 * @return 
 * @apiNote:
 */

package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 雇员简历
 */
public class EmployeeHistory {
    private Long id;//简历表ID

    private Long userId;//用户id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;//开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间

    private String descript;//描述

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;//本记录的创建时间

    private Long byWho;//本记录的写入者

    private Boolean deleted;//本记录是否有效 0:有效 1:无效

    //简历表ID的get方法
    public Long getId() {
        return id;
    }

    //简历表ID的set方法
    public void setId(Long id) {
        this.id = id;
    }
    //用户id的get方法
    public Long getUserId() {
        return userId;
    }
    //用户id的set方法
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //开始时间的get方法
    public Date getBeginTime() {
        return beginTime;
    }
    //开始时间的set方法
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    //结束时间的get方法
    public Date getEndTime() {
        return endTime;
    }
    //结束时间的set方法
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    //描述的get方法
    public String getDescript() {
        return descript;
    }
    //描述的set方法
    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }
    //本记录的创建时间的get方法
    public Date getDatetime() {
        return datetime;
    }
    //本记录的创建时间的set方法
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    //本记录的写入者的get方法
    public Long getByWho() {
        return byWho;
    }
    //本记录的写入者的set方法
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }
    //本记录是否有效的get方法
    public Boolean getDeleted() {
        return deleted;
    }
    //本记录是否有效的set方法
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "EmployeeHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", descript='" + descript + '\'' +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public EmployeeHistory(Long id, Long userId, Date beginTime, Date endTime, String descript, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.descript = descript;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    public EmployeeHistory() {
    }
}