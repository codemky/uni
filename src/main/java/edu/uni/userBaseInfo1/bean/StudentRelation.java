/**
 * Author: mokuanyuan 14:25 2019/5/5
 * @apiNote: 学生亲属表实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudentRelation {
//    学生亲属表
    private Long id;
//    用户ID
    private Long userId;
//    亲属姓名
    private String relaName;
//    亲属在本系统的id  关联到user.id
    private Long relaId;
//    关系0：母  1：父 2：兄  3：弟 4：姐  5：妹6: 其他
    private Integer relationship;
//    本记录的创建时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
//    本记录的写入者
    private Long byWho;
//    记录是否有效  0：有效  1：无效
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRelaName() {
        return relaName;
    }

    public void setRelaName(String relaName) {
        this.relaName = relaName == null ? null : relaName.trim();
    }

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
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
        return "StudentRelation{" +
                "id=" + id +
                ", userId=" + userId +
                ", relaName='" + relaName + '\'' +
                ", relaId=" + relaId +
                ", relationship=" + relationship +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}