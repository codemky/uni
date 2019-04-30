/**
 * Author: mokuanyuan 16:55 2019/4/30
 * @param
 * @return
 * @apiNote: 省份/州表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AddrState {
//    省份/州表id
    private Long id;
//    国家表id
    private Long countryId;
//    中文名
    private String stateZh;
//    英文名
    private String stateEn;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getStateZh() {
        return stateZh;
    }

    public void setStateZh(String stateZh) {
        this.stateZh = stateZh == null ? null : stateZh.trim();
    }

    public String getStateEn() {
        return stateEn;
    }

    public void setStateEn(String stateEn) {
        this.stateEn = stateEn == null ? null : stateEn.trim();
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