package edu.uni.userBaseInfo1.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 *  电子通信方式
 */
public class Ecomm {
    //电子通信方式ID
    private Long id;

    //用户ID
    private Long userId;

    //具体内容
    private String content;

    // 电子通信方式
    // 0: QQ号   1: 微信号
    //2:电子邮箱  3: 移动电话号码
    //4: 办公室号码 5: 家庭电话
    private Integer flag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //本记录的创建时间
    private Date datetime;

    //本记录的写入者
    private Long byWho;

    //本记录是否有效 0:有效 1:无效
    private Boolean deleted;

    //电子通信方式ID的get方法
    public Long getId() {
        return id;
    }
    //电子通信方式ID的set方法
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
    //具体内容的get方法
    public String getContent() {
        return content;
    }
    //具体内容的set方法
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    // 电子通信方式的get方法
    public Integer getFlag() {
        return flag;
    }
    // 电子通信方式的set方法
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
    //本记录是否有效的get方法
    public Boolean getDeleted() {
        return deleted;
    }
    //本记录是否有效的set方法
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Ecomm{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", flag=" + flag +
                ", datetime=" + datetime +
                ", byWho=" + byWho +
                ", deleted=" + deleted +
                '}';
    }

    public static boolean isValidForApply(Ecomm ecomm){
        return ecomm.getId() != null && ecomm.getContent() != null && ecomm.getFlag() != null;
    }

    public static void copyPropertiesForApply (Ecomm newEcomm , Ecomm oldEcomm){
        newEcomm.setDatetime(new Date());
        newEcomm.setUserId(oldEcomm.getUserId());
        newEcomm.setDeleted(true);

    }

    public Ecomm(Long id, Long userId, String content, Integer flag, Date datetime, Long byWho, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.flag = flag;
        this.datetime = datetime;
        this.byWho = byWho;
        this.deleted = deleted;
    }

    public Ecomm() {
    }
}