package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 地址表
 */
public class Address {

    //地址ID
    private Long id;
    //用户ID
    private Long userId;

    //国家 关联到country.id
    private Long country;

    //省份/州  关联到state.id
    private Long state;

    //城市  关联到city.id
    private Long city;

    //县/区  关联到area.id
    private Long area;

    //村/街道  关联到street.id
    private Long street;

    //详细尾缀
    private String detail;

    //邮政编码
    private String zipCode;

    //收件人电话
    private String telephone;

    //0: 当前住址  1: 收件地址
    //2: 曾经住址  3: 通信地址
    //4: 办公地址
    private Integer flag;

    //本记录的创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    //地址ID的get方法
    public Long getId() {
        return id;
    }
    //地址ID的set方法
    public void setId(Long id) {
        this.id = id;
    }
    //用户ID的get方法
    public Long getUserId() {
        return userId;
    }
    //用户ID的set方法
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    //国家 关联到country.id的get方法
    public Long getCountry() {
        return country;
    }
    //国家 关联到country.id的set方法
    public void setCountry(Long country) {
        this.country = country;
    }
    //省份/州  关联到state.id的get方法
    public Long getState() {
        return state;
    }
    //省份/州  关联到state.id的set方法
    public void setState(Long state) {
        this.state = state;
    }
    //城市  关联到city.id的get方法
    public Long getCity() {
        return city;
    }
    //城市  关联到city.id的set方法
    public void setCity(Long city) {
        this.city = city;
    }
    //县/区  关联到area.id的get方法
    public Long getArea() {
        return area;
    }
    //县/区  关联到area.id的set方法
    public void setArea(Long area) {
        this.area = area;
    }
    //村/街道  关联到street.id的get方法
    public Long getStreet() {
        return street;
    }
    //村/街道  关联到street.id的set方法
    public void setStreet(Long street) {
        this.street = street;
    }
    //详细尾缀的get方法
    public String getDetail() {
        return detail;
    }
    //详细尾缀的set方法
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
    //邮政编码的get方法
    public String getZipCode() {
        return zipCode;
    }
    //邮政编码的set方法
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }
    //收件人电话的get方法
    public String getTelephone() {
        return telephone;
    }
    //收件人电话的set方法
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
    //0: 当前住址  1: 收件地址
    //    //2: 曾经住址  3: 通信地址
    //    //4: 办公地址的get方法
    public Integer getFlag() {
        return flag;
    }
    //0: 当前住址  1: 收件地址
    //2: 曾经住址  3: 通信地址
    //4: 办公地址
    // 的set方法
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    //本记录的创建时间的get方法
    public Date getDatetime() {
        return datetime;
    }
    //本记录的创建时间的set方法
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    //本记录的写入者的get方法
    public Long getByWho() {
        return byWho;
    }
    //本记录的写入者的set方法
    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }
    //本记录是否有效 0:有效 1:无效的get方法
    public Boolean getDeleted() {
        return deleted;
    }
    //本记录是否有效 0:有效 1:无效的set方法
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", country=" + country +
                ", state=" + state +
                ", city=" + city +
                ", area=" + area +
                ", street=" + street +
                ", detail='" + detail + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", telephone='" + telephone + '\'' +
                ", flag=" + flag +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public Address(Long id, Long userId, Long country, Long state, Long city, Long area, Long street, String detail, String zipCode, String telephone, Integer flag, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.area = area;
        this.street = street;
        this.detail = detail;
        this.zipCode = zipCode;
        this.telephone = telephone;
        this.flag = flag;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    public Address() {
    }
}