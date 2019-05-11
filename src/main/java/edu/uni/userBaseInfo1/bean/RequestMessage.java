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
    private Long byWho;
    private UserinfoApply userinfoApply;

    public StudentRelation getStudentRelation() {
        return studentRelation;
    }

    public void setStudentRelation(StudentRelation studentRelation) {
        this.studentRelation = studentRelation;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Ecomm getEcomm() {
        return ecomm;
    }

    public void setEcomm(Ecomm ecomm) {
        this.ecomm = ecomm;
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

    public RequestMessage() {
    }

    public RequestMessage(Ecomm ecomm, Long byWho, UserinfoApply userinfoApply) {
        this.ecomm = ecomm;
        this.byWho = byWho;
        this.userinfoApply = userinfoApply;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "ecomm=" + ecomm +
                ", byWho=" + byWho +
                ", userinfoApply=" + userinfoApply +
                '}';
    }
}
