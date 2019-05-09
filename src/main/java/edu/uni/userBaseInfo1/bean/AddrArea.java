package edu.uni.userBaseInfo1.bean;

public class AddrArea {
    private Long id;

    private Long cityCode;

    private String areaZh;

    private String areaEn;

    private Long code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AddrArea{" +
                "id=" + id +
                ", cityCode=" + cityCode +
                ", areaZh='" + areaZh + '\'' +
                ", areaEn='" + areaEn + '\'' +
                ", code=" + code +
                '}';
    }
}