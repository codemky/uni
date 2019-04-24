/*
author:  zhangzhanqiao
create:  2019.4.24
modified:  2019.4.24
description：照片表
*/
package edu.uni.userBaseInfo1.bean;

import java.util.Date;

/*
picture实体类 照片表
（如果有需要说明，可以放在这里）
*/
public class Picture {
    // picture数据表唯一编号ID
    private Long id;
    // 用户ID
    private Long userId;
    // 学校ID
    private Long universityId;
    // 个人照片都集中存放在一个文件
    private String pictureName;
    // 类型 0:证件照 1:生活照
    private Boolean flag;
    // 本记录的创建时间
    private Date datetime;
    // 本记录的写入者
    private Long byWho;
    // 本记录是否有效 0:有效 1:无效
    private Boolean deleted;
    // 获取数据表唯一编号id
    public Long getId() {
        return id;
    }
    //设置数据表唯一编号id
    public void setId(Long id) {
        this.id = id;
    }
    //获取用户id
    public Long getUserId() {
        return userId;
    }
    //设置用户id
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //获取学校id
    public Long getUniversityId() {
        return universityId;
    }
    //设置学校id
    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }
    //获取个人照片
    public String getPictureName() {
        return pictureName;
    }
    //设置个人照片
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }
    //获取照片类型
    public Boolean getFlag() {
        return flag;
    }
    //设置照片类型
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    //获取本记录创建时间
    public Date getDatetime() {
        return datetime;
    }
    //设置本记录创建时间
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    //获取本记录的写入者
    public Long getByWho() {
        return byWho;
    }
    //设置本记录的写入者
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }
    //获取本记录是否有效
    public Boolean getDeleted() {
        return deleted;
    }
    //设置本记录是否有效
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    //本实体类的toString()方法
    @java.lang.Override
    public java.lang.String toString() {
        return "Picture{" +
                "id=" + id +
                ", userId=" + userId +
                ", universityId=" + universityId +
                ", pictureName='" + pictureName + '\'' +
                ", flag=" + flag +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}