package edu.uni.userBaseInfo1.bean;

/**
 * @Author laizhouhao
 * @Description 用于储存请求参数实体集
 * @Date 11:37 2019/5/10
 **/
public class RequestMessage {
    private Ecomm ecomm;
    private Address address;
    private Picture picture;
    private StudentRelation studentRelation;
    private LearningDegree learningDegree;
    private EmployeeHistory employeeHistory;
    private Student student;
    private Employee employee;
    private UserUploadFile userUploadFile;
    private Long byWho;
    private UserinfoApply userinfoApply;

    public RequestMessage() {
    }


    public Ecomm getEcomm() {
        return ecomm;
    }

    public void setEcomm(Ecomm ecomm) {
        this.ecomm = ecomm;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public StudentRelation getStudentRelation() {
        return studentRelation;
    }

    public void setStudentRelation(StudentRelation studentRelation) {
        this.studentRelation = studentRelation;
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public UserinfoApply getUserinfoApply() {
        return userinfoApply;
    }

    public void setUserinfoApply(UserinfoApply userinfoApply) {
        this.userinfoApply = userinfoApply;
    }

    public LearningDegree getLearningDegree() {
        return learningDegree;
    }

    public void setLearningDegree(LearningDegree learningDegree) {
        this.learningDegree = learningDegree;
    }

    public EmployeeHistory getEmployeeHistory() {
        return employeeHistory;
    }

    public void setEmployeeHistory(EmployeeHistory employeeHistory) {
        this.employeeHistory = employeeHistory;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public UserUploadFile getUserUploadFile() {
        return userUploadFile;
    }

    public void setUserUploadFile(UserUploadFile userUploadFile) {
        this.userUploadFile = userUploadFile;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "ecomm=" + ecomm +
                ", address=" + address +
                ", picture=" + picture +
                ", studentRelation=" + studentRelation +
                ", learningDegree=" + learningDegree +
                ", employeeHistory=" + employeeHistory +
                ", student=" + student +
                ", employee=" + employee +
                ", userUploadFile=" + userUploadFile +
                ", byWho=" + byWho +
                ", userinfoApply=" + userinfoApply +
                '}';
    }

    public RequestMessage(Ecomm ecomm, Address address, Picture picture, StudentRelation studentRelation, LearningDegree learningDegree, EmployeeHistory employeeHistory, Student student, Employee employee, UserUploadFile userUploadFile, Long byWho, UserinfoApply userinfoApply) {
        this.ecomm = ecomm;
        this.address = address;
        this.picture = picture;
        this.studentRelation = studentRelation;
        this.learningDegree = learningDegree;
        this.employeeHistory = employeeHistory;
        this.student = student;
        this.employee = employee;
        this.userUploadFile = userUploadFile;
        this.byWho = byWho;
        this.userinfoApply = userinfoApply;
    }
}
