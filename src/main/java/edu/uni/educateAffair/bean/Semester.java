package edu.uni.educateAffair.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Semester {
    private Long id;

    private Long universityId;

    private String name;

    private Date start;

    private Date end;

    private Date datetime;

    private Long byWho;

    private Integer deleted;

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }
}