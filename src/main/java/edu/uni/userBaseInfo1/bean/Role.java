package edu.uni.userBaseInfo1.bean;

public class Role {
    private Long id;

    private String name;

    private String description;

    private Long universityId;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Role(Long id, String name, String description, Long universityId, Integer status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.universityId = universityId;
        this.status = status;
    }

    public Role() {
    }
}