/**
 * Author: mokuanyuan 16:56 2019/4/30
 * @param
 * @return
 * @apiNote: 村/街道表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AddrStreet {
//    村/街道表 id
    private Long id;
//    县/区表id
    private Long areaId;
//    中文名
    private String streetZh;
//    英文名
    private String streetEn;
//    创建时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
//    创建人
    private Long byWho;
//    记录是否有效
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getStreetZh() {
        return streetZh;
    }

    public void setStreetZh(String streetZh) {
        this.streetZh = streetZh == null ? null : streetZh.trim();
    }

    public String getStreetEn() {
        return streetEn;
    }

    public void setStreetEn(String streetEn) {
        this.streetEn = streetEn == null ? null : streetEn.trim();
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