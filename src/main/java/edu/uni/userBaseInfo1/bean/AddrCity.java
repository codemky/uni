package edu.uni.userBaseInfo1.bean;

public class AddrCity {
    private Long id;

    private String cityZh;

    private String cityEn;

    private Long code;

    private Long stateCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getStateCode() {
        return stateCode;
    }

    public void setStateCode(Long stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "AddrCity{" +
                "id=" + id +
                ", cityZh='" + cityZh + '\'' +
                ", cityEn='" + cityEn + '\'' +
                ", code=" + code +
                ", stateCode=" + stateCode +
                '}';
    }
}