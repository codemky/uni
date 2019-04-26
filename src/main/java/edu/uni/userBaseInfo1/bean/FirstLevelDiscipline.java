/**
 * Author: caiguangqian 8:27 2019/4/25
 * @param 
 * @return 
 * @apiNote:
 */
package edu.uni.userBaseInfo1.bean;

/**
 *  一级学科
 */
public class FirstLevelDiscipline {
    private Long id;//一级学科表id

    private Long categoryId;//关联到学科类别表id

    private String discipline;//具体的一级学科名称
    //一级学科表id的get方法
    public Long getId() {
        return id;
    }
    //一级学科表id的set方法
    public void setId(Long id) {
        this.id = id;
    }
    //关联到学科类别表id的get方法
    public Long getCategoryId() {
        return categoryId;
    }
    //关联到学科类别表id的set方法
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    //具体的一级学科名称的get方法
    public String getDiscipline() {
        return discipline;
    }
    //具体的一级学科名称的set方法
    public void setDiscipline(String discipline) {
        this.discipline = discipline == null ? null : discipline.trim();
    }

    @Override
    public String toString() {
        return "FirstLevelDiscipline{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", discipline='" + discipline + '\'' +
                '}';
    }

    public FirstLevelDiscipline(Long id, Long categoryId, String discipline) {
        this.id = id;
        this.categoryId = categoryId;
        this.discipline = discipline;
    }

    public FirstLevelDiscipline() {
    }
}