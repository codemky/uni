/*
author:  张展侨
create:  2019.4.24
modified:  2019.4.24
description：政治面貌表
*/
package edu.uni.userBaseInfo1.bean;
/*
PoliticalAffiliation实体类 政治面貌表
（如果有需要说明，可以放在这里）
*/
public class PoliticalAffiliation {
    // political_affiliation数据表唯一编号ID
    private Long id;
    // 政治面貌具体内容
    private String political;
    // 获取political_affiliation数据表唯一编号ID
    public Long getId() {
        return id;
    }
    //设置political_affiliation数据表唯一编号ID
    public void setId(Long id) {
        this.id = id;
    }
    //获取政治面貌的具体内容
    public String getPolitical() {
        return political;
    }
    //设置政治面貌的具体内容
    public void setPolitical(String political) {
        this.political = political == null ? null : political.trim();
    }
    //本实体类的toString()方法
    @java.lang.Override
    public java.lang.String toString() {
        return "PoliticalAffiliation{" +
                "id=" + id +
                ", political='" + political + '\'' +
                '}';
    }
}