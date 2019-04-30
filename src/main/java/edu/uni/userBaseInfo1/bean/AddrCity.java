/**
 * Author: mokuanyuan 16:50 2019/4/30
 * @param
 * @return
 * @apiNote: 城市表的实体类
 */
package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AddrCity {
//    城市表id
    private Long id;
//    省份/州表id
    private Long stateId;
//    中文名
    private String cityZh;
//    英文名
    private String cityEn;
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

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh == null ? null : cityZh.trim();
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn == null ? null : cityEn.trim();
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