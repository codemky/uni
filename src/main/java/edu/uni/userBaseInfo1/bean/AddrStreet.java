package edu.uni.userBaseInfo1.bean;

public class AddrStreet {
    private Long id;

    private Long areaCode;

    private String streetZh;

    private String streetEn;

    private Long code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AddrStreet{" +
                "id=" + id +
                ", areaCode=" + areaCode +
                ", streetZh='" + streetZh + '\'' +
                ", streetEn='" + streetEn + '\'' +
                ", code=" + code +
                '}';
    }
}