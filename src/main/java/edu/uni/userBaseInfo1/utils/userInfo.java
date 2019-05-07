package edu.uni.userBaseInfo1.utils;

import edu.uni.userBaseInfo1.bean.*;

import java.util.List;

/**
 * Author: mokuanyuan 15:53 2019/5/7
 * @apiNote: 用于把所有跟user_id有关的数据记录关联到一起
 */
public class userInfo {

    List<User> users;
    List<Ecomm> ecomms;
    List<Address> addresses;
    List<Picture> pictures;
    List<StudentRelation> studentRelations;
    List<LearningDegree> learningDegrees;
    List<EmployeeHistory> employeeHistories;
    List<Student> students;
    List<Employee> employees;
    List<UserinfoApply> userinfoApplies;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public List<LearningDegree> getLearningDegrees() {
        return learningDegrees;
    }

    public void setLearningDegrees(List<LearningDegree> learningDegrees) {
        this.learningDegrees = learningDegrees;
    }

    public List<EmployeeHistory> getEmployeeHistories() {
        return employeeHistories;
    }

    public void setEmployeeHistories(List<EmployeeHistory> employeeHistories) {
        this.employeeHistories = employeeHistories;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<UserinfoApply> getUserinfoApplies() {
        return userinfoApplies;
    }

    public void setUserinfoApplies(List<UserinfoApply> userinfoApplies) {
        this.userinfoApplies = userinfoApplies;
    }


    public userInfo(List<User> users, List<Ecomm> ecomms, List<Address> addresses, List<Picture> pictures, List<StudentRelation> studentRelations, List<LearningDegree> learningDegrees, List<EmployeeHistory> employeeHistories, List<Student> students, List<Employee> employees, List<UserinfoApply> userinfoApplies) {
        this.users = users;
        this.ecomms = ecomms;
        this.addresses = addresses;
        this.pictures = pictures;
        this.studentRelations = studentRelations;
        this.learningDegrees = learningDegrees;
        this.employeeHistories = employeeHistories;
        this.students = students;
        this.employees = employees;
        this.userinfoApplies = userinfoApplies;
    }

    @Override
    public String toString() {
        return "userInfo{" +
                "users=" + users +
                ", ecomms=" + ecomms +
                ", addresses=" + addresses +
                ", pictures=" + pictures +
                ", studentRelations=" + studentRelations +
                ", learningDegrees=" + learningDegrees +
                ", employeeHistories=" + employeeHistories +
                ", students=" + students +
                ", employees=" + employees +
                ", userinfoApplies=" + userinfoApplies +
                '}';
    }
}
