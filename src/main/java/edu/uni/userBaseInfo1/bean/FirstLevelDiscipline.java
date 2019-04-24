package edu.uni.userBaseInfo1.bean;

public class FirstLevelDiscipline {
    private Long id;

    private Long categoryId;

    private String discipline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

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