/**
 * Author: caiguangqian 8:27 2019/4/25
 * @param 
 * @return 
 * @apiNote:
 */

package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class EmployeeHistory {
    //简历表ID
    private Long id;
    //用户ID
    private Long userId;
    //开始时间
    private Date beginTime;
    //结束时间
    private Date endTime;
    //描述
    private String descript;
    //本记录的创建时间
    private Date datetime;
    //本记录的写入者
    private Long byWho;
    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

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
    //读简历表ID
    public Long getId() {
        return id;
    }
    //写简历表ID
    public void setId(Long id) {
        this.id = id;
    }
    //读用户ID
    public Long getUserId() {
        return userId;
    }
    //写用户ID
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
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
}