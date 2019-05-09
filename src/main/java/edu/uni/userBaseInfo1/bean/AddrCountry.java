package edu.uni.userBaseInfo1.bean;

public class AddrCountry {
    private Long id;

    private String countryZh;

    private String countryEn;

    private Long code;

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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AddrCountry{" +
                "id=" + id +
                ", countryZh='" + countryZh + '\'' +
                ", countryEn='" + countryEn + '\'' +
                ", code=" + code +
                '}';
    }
}