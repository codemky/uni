package edu.uni.userBaseInfo1.bean;

/**
 * 学科类别表
 */
public class DisciplineCategory {
    //学科类别表id
    private Long id;
    //具体的学科类别名称
    private String discipline;

    //学科类别表id的get方法
    public Long getId() {
        return id;
    }

    //具体的学科类别名称的set方法
    public void setId(Long id) {
        this.id = id;
    }
    //具体的学科类别名称的get方法
    public String getDiscipline() {
        return discipline;
    }

    //具体的学科类别名称的set方法
    public void setDiscipline(String discipline) {
        this.discipline = discipline == null ? null : discipline.trim();
    }

    @Override
    public String toString() {
        return "DisciplineCategory{" +
                "id=" + id +
                ", discipline='" + discipline + '\'' +
                '}';
    }

    public DisciplineCategory(Long id, String discipline) {
        this.id = id;
        this.discipline = discipline;
    }

    public DisciplineCategory() {
    }
}