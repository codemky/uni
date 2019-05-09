package edu.uni.userBaseInfo1.bean;

public class AddrState {
    private Long id;

    private Long countryCode;

    private String stateZh;

    private String stateEn;

    private Long code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AddrState{" +
                "id=" + id +
                ", countryCode=" + countryCode +
                ", stateZh='" + stateZh + '\'' +
                ", stateEn='" + stateEn + '\'' +
                ", code=" + code +
                '}';
    }
}