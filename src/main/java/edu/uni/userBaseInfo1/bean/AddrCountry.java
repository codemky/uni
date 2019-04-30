/**
 * Author: mokuanyuan 16:52 2019/4/30
 * @param
 * @return
 * @apiNote: 国家表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AddrCountry {
//    国家表id
    private Long id;
//    中文名
    private String countryZh;
//    英文名
    private String countryEn;
//    记录创建时间
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
//    创建人
    private Long byWho;
//    是否有效 0：有效  1：无效
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryZh() {
        return countryZh;
    }

    public void setCountryZh(String countryZh) {
        this.countryZh = countryZh == null ? null : countryZh.trim();
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn == null ? null : countryEn.trim();
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