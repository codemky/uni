package edu.uni.userBaseInfo1.bean;

public class PoliticalAffiliation {
    private Long id;

    private String political;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political == null ? null : political.trim();
    }
}