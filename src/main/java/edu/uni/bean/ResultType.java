package edu.uni.bean;

public enum ResultType {
    Exception(-3, "抛出异常"),
    Disallow(-2,"服务器不允许"),
    ParamError(-1,"参数错误"),
    Success(0,"操作成功"),
    Failed(1, "操作失败"),
    Continue(2, "请继续后续步骤"),
    Unauthorized(3, "未授权"),
    ServiceUnable(4, "当前服务不可用"),
    SuccessAndRedirect(5, "操作成功，跳转页面"),
    FailedAndRedirect(6, "操作失败，跳转页面");

    private int CODE;
    private String MSG;

    ResultType(int code, String msg){   CODE=code;    MSG=msg;    }

    public int getCODE() {
        return CODE;
    }

    public void setCODE(int CODE) {
        this.CODE = CODE;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }
}