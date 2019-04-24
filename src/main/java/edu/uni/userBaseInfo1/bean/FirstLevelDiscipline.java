/*
author: caiguangqian
create:  2019.4.24
modified:  2019.4.24
description：一级学科的实体类
*/
package edu.uni.userBaseInfo1.bean;

public class FirstLevelDiscipline {
    //一级学科表id
    private Long id;
    //关联到学科类别表id
    private Long categoryId;
    //具体的一级学科名称
    private String discipline;

    @Override
    public String toString() {
        return "FirstLevelDiscipline{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", discipline='" + discipline + '\'' +
                '}';
    }
    //获取一级学科表id
    public Long getId() {
        return id;
    }
    //写一级学科表id
    public void setId(Long id) {
        this.id = id;
    }
    //获取关联到学科类别表id
    public Long getCategoryId() {
        return categoryId;
    }
    //写入关联到学科类别表id
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline == null ? null : discipline.trim();
    }
}