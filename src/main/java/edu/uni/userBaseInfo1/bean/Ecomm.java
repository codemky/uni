package edu.uni.userBaseInfo1.bean;

import java.util.Date;

public class Ecomm {
    private Long id;

    private Long userId;

    private String content;

    private Integer flag;

    private Date datetime;

    private Long byWho;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
        return "Ecomm{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", flag=" + flag +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public Ecomm(Long id, Long userId, String content, Integer flag, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.flag = flag;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

}