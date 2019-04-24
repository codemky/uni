/*
author:  张展侨
create:  2019.4.24
modified:  2019.4.24
description：二级学科表
*/
package edu.uni.userBaseInfo1.bean;

/*
SecondLevelDiscipline实体类 二级学科表
（如果有需要说明，可以放在这里）
*/
public class SecondLevelDiscipline {
    // SecondLevelDiscipline数据表唯一编号ID
    private Long id;
    // 关联到一级学科表id
    private Long firstId;
    // 具体的二级学科名称
    private String categoryId;
    // 获取SecondLevelDiscipline数据表唯一编号ID
    public Long getId() {
        return id;
    }
    // 设置SecondLevelDiscipline数据表唯一编号ID
    public void setId(Long id) {
        this.id = id;
    }
    // 获取关联到一级学科表id
    public Long getFirstId() {
        return firstId;
    }
    // 设置关联到一级学科表id
    public void setFirstId(Long firstId) {
        this.firstId = firstId;
    }
    // 获取具体的二级学科名称
    public String getCategoryId() {
        return categoryId;
    }
    // 设置具体的二级学科名称
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }
    //本实体类的toString()方法
    @java.lang.Override
    public java.lang.String toString() {
        return "SecondLevelDiscipline{" +
                "id=" + id +
                ", firstId=" + firstId +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}