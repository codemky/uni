package edu.uni.userBaseInfo1.bean;

public class DisciplineCategory {
    private Long id;

    private String discipline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline == null ? null : discipline.trim();
    }
}