package edu.uni.userBaseInfo1.PageBean;

/**
 * @Author chenenru
 * @ClassName EmployeeBean
 * @Description
 * @Date 2019/6/22 19:52
 * @Version 1.0
 **/
public class EmployeeBean {
    //员工编号,姓名，学校，当前所属学院，当前所在科室，行政岗位
    /**
     *       "universityName": "肇庆学院",
     *       "departmentName": "大数据科学学院",
     *       "positionName": "授课老师",
     *       "subDepartmentName": "教材科",
     *       "employeeNo": "2010011",
     *       "username": "运营者2"
     */
    private Long employeeId;
    private String employeeNo;
    private String username;
    private String universityName;
    private String departmentName;
    private String subDepartmentName;
    private String positionName;

    public EmployeeBean() {
    }

    public EmployeeBean(Long employeeId, String employeeNo, String username, String universityName, String departmentName, String subDepartmentName, String positionName) {
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.username = username;
        this.universityName = universityName;
        this.departmentName = departmentName;
        this.subDepartmentName = subDepartmentName;
        this.positionName = positionName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSubDepartmentName() {
        return subDepartmentName;
    }

    public void setSubDepartmentName(String subDepartmentName) {
        this.subDepartmentName = subDepartmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId=" + employeeId +
                ", employeeNo='" + employeeNo + '\'' +
                ", username='" + username + '\'' +
                ", universityName='" + universityName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", subDepartmentName='" + subDepartmentName + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
