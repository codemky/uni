package edu.uni.userBaseInfo1.utils;

import edu.uni.administrativestructure.bean.Department;
import edu.uni.administrativestructure.bean.Subdepartment;
import edu.uni.professionalcourses.bean.Academic;
import edu.uni.professionalcourses.bean.AcademicDegree;
import edu.uni.professionalcourses.bean.SecondLevelDiscipline;
import edu.uni.professionalcourses.bean.Specialty;
import edu.uni.userBaseInfo1.bean.*;

import java.util.List;

/**
 * ClassName UserBaseInfo
 * Description TODO
 * Author LonelySeven
 * Date 2019/6/7 16:27
 * Version 1.0
 **/
public class UserBaseInfo {

    User user;
    Student student;
    Employee employee;
    List<Ecomm> ecomms;
    List<Address> addresses;
    List<Picture> pictures;
    List<StudentRelation> studentRelations;
    LearningDegree learningDegree;
    EmployeeHistory employeeHistory;
    PoliticalAffiliation politicalAffiliation;
    Academic academic;
    AcademicDegree academicDegree;

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "user=" + user +
                ", student=" + student +
                ", employee=" + employee +
                ", ecomms=" + ecomms +
                ", addresses=" + addresses +
                ", pictures=" + pictures +
                ", studentRelations=" + studentRelations +
                ", learningDegree=" + learningDegree +
                ", employeeHistory=" + employeeHistory +
                ", politicalAffiliation=" + politicalAffiliation +
                ", academic=" + academic +
                ", academicDegree=" + academicDegree +
                '}';
    }

    public UserBaseInfo(User user, Student student, Employee employee, List<Ecomm> ecomms, List<Address> addresses, List<Picture> pictures, List<StudentRelation> studentRelations, LearningDegree learningDegree, EmployeeHistory employeeHistory, PoliticalAffiliation politicalAffiliation, Academic academic, AcademicDegree academicDegree) {
        this.user = user;
        this.student = student;
        this.employee = employee;
        this.ecomms = ecomms;
        this.addresses = addresses;
        this.pictures = pictures;
        this.studentRelations = studentRelations;
        this.learningDegree = learningDegree;
        this.employeeHistory = employeeHistory;
        this.politicalAffiliation = politicalAffiliation;
        this.academic = academic;
        this.academicDegree = academicDegree;
    }

    public UserBaseInfo() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Ecomm> getEcomms() {
        return ecomms;
    }

    public void setEcomms(List<Ecomm> ecomms) {
        this.ecomms = ecomms;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<StudentRelation> getStudentRelations() {
        return studentRelations;
    }

    public void setStudentRelations(List<StudentRelation> studentRelations) {
        this.studentRelations = studentRelations;
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

    public PoliticalAffiliation getPoliticalAffiliation() {
        return politicalAffiliation;
    }

    public void setPoliticalAffiliation(PoliticalAffiliation politicalAffiliation) {
        this.politicalAffiliation = politicalAffiliation;
    }

    public Academic getAcademic() {
        return academic;
    }

    public void setAcademic(Academic academic) {
        this.academic = academic;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }
}
