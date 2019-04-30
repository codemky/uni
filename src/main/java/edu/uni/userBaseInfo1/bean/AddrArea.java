/**
 * Author: mokuanyuan 16:47 2019/4/30
 * @param
 * @return
 * @apiNote: 县/区表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AddrArea {
//    县/区表id
    private Long id;
//    城市表id
    private Long cityId;
//    中文名
    private String areaZh;
//    英文名
    private String areaEn;
//    创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAreaZh() {
        return areaZh;
    }

    public void setAreaZh(String areaZh) {
        this.areaZh = areaZh == null ? null : areaZh.trim();
    }

    public String getAreaEn() {
        return areaEn;
    }

    public void setAreaEn(String areaEn) {
        this.areaEn = areaEn == null ? null : areaEn.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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