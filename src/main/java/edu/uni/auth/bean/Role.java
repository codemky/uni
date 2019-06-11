package edu.uni.auth.bean;

public class Role {
    public static final String Operator = "Operator";
    public static final String UnivInfoSUP = "UnivInfoSUP";
    public static final long OperatorId = 1;
    public static final long UnivInfoSUPId = 2;

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", universityId=" + universityId +
                ", status=" + status +
                '}';
    }
}