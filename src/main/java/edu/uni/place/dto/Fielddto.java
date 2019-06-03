package edu.uni.place.dto;

import java.util.Date;

/**
 * 多表查询：根据校区id查校区名、功能区查功能区名、场地名模糊查询。
 * @Author 潘绍豪
 * @Create 2019/5/11
 * @Description
 * @Since 1.0.0
 */
public class Fielddto {
    private Long id;

    private Long universityId;

    private Long schoolId;

    private Long areaId;

    private String name;

    private String schoolname;

    private String areaname;

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
