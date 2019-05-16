package edu.uni.userBaseInfo1.utils;

import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.bean.Class;

import java.util.List;

/**
 * Author: mokuanyuan 15:53 2019/5/7
 * @apiNote: 用于把所有跟user_id有关的数据记录关联到一起
 */
public class UserInfo {

    private List<User> users;
    private List<Ecomm> ecomms;
    private List<Address> addresses;
    private List<Picture> pictures;
    private List<StudentRelation> studentRelations;
    private List<LearningDegree> learningDegrees;
    private List<EmployeeHistory> employeeHistories;
    private List<Student> students;
    private List<Employee> employees;
    private List<UserinfoApply> userinfoApplies;
    private List<PoliticalAffiliation> politicalAffiliations;
    private List<AddrCountry>addrCountries;
    private List<AddrState> addrStates;
    private List<AddrCity>addrCities;
    private List<AddrArea>addrAreas;
    private List<AddrStreet>addrStreets;
    private List<Academic> academics;
    private List<AcademicDegree> academicDegrees;
    private List<GetAddrDetail>getAddrDetails;
    private List<Class> classes;
    private List<Classmate> classmates;

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Classmate> getClassmates() {
        return classmates;
    }

    public void setClassmates(List<Classmate> classmates) {
        this.classmates = classmates;
    }

    public List<GetAddrDetail> getGetAddrDetails() {
        return getAddrDetails;
    }

    public void setGetAddrDetails(List<GetAddrDetail> getAddrDetails) {
        this.getAddrDetails = getAddrDetails;
    }

    public List<Academic> getAcademics() {
        return academics;
    }

    public void setAcademics(List<Academic> academics) {
        this.academics = academics;
    }

    public List<AcademicDegree> getAcademicDegrees() {
        return academicDegrees;
    }

    public void setAcademicDegrees(List<AcademicDegree> academicDegrees) {
        this.academicDegrees = academicDegrees;
    }

    public List<AddrCountry> getAddrCountries() {
        return addrCountries;
    }

    public void setAddrCountries(List<AddrCountry> addrCountries) {
        this.addrCountries = addrCountries;
    }

    public List<AddrState> getAddrStates() {
        return addrStates;
    }

    public void setAddrStates(List<AddrState> addrStates) {
        this.addrStates = addrStates;
    }

    public List<AddrCity> getAddrCities() {
        return addrCities;
    }

    public void setAddrCities(List<AddrCity> addrCities) {
        this.addrCities = addrCities;
    }

    public List<AddrArea> getAddrAreas() {
        return addrAreas;
    }

    public void setAddrAreas(List<AddrArea> addrAreas) {
        this.addrAreas = addrAreas;
    }

    public List<AddrStreet> getAddrStreets() {
        return addrStreets;
    }

    public void setAddrStreets(List<AddrStreet> addrStreets) {
        this.addrStreets = addrStreets;
    }

    public List<PoliticalAffiliation> getPoliticalAffiliations() {
        return politicalAffiliations;
    }

    public void setPoliticalAffiliations(List<PoliticalAffiliation> politicalAffiliations) {
        this.politicalAffiliations = politicalAffiliations;
    }

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


    public UserInfo() {
    }

    public UserInfo(List<User> users, List<Ecomm> ecomms, List<Address> addresses, List<Picture> pictures, List<StudentRelation> studentRelations, List<LearningDegree> learningDegrees, List<EmployeeHistory> employeeHistories, List<Student> students, List<Employee> employees, List<UserinfoApply> userinfoApplies, List<PoliticalAffiliation> politicalAffiliations, List<AddrCountry> addrCountries, List<AddrState> addrStates, List<AddrCity> addrCities, List<AddrArea> addrAreas, List<AddrStreet> addrStreets, List<Academic> academics, List<AcademicDegree> academicDegrees, List<GetAddrDetail> getAddrDetails, List<Class> classes, List<Classmate> classmates) {
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
        this.politicalAffiliations = politicalAffiliations;
        this.addrCountries = addrCountries;
        this.addrStates = addrStates;
        this.addrCities = addrCities;
        this.addrAreas = addrAreas;
        this.addrStreets = addrStreets;
        this.academics = academics;
        this.academicDegrees = academicDegrees;
        this.getAddrDetails = getAddrDetails;
        this.classes = classes;
        this.classmates = classmates;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
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
                ", politicalAffiliations=" + politicalAffiliations +
                ", addrCountries=" + addrCountries +
                ", addrStates=" + addrStates +
                ", addrCities=" + addrCities +
                ", addrAreas=" + addrAreas +
                ", addrStreets=" + addrStreets +
                ", academics=" + academics +
                ", academicDegrees=" + academicDegrees +
                ", getAddrDetails=" + getAddrDetails +
                ", classes=" + classes +
                ", classmates=" + classmates +
                '}';
    }
}
