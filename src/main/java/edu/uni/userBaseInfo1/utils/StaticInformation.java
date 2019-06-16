package edu.uni.userBaseInfo1.utils;

/**
 * ClassName StaticInformation
 * Description TODO
 * Author LonelySeven
 * Date 2019/6/11 18:56
 * Version 1.0
 **/
public class StaticInformation {

    /**
     * Author: mokuanyuan 18:57 2019/6/11
     * @param userType
     * @param infoType
     * @param flag
     * @return String
     * @apiNote: 根据申请用户类型和申请的信息类型返回相应的业务名称（用字符串拼接方法）
     */
    public static String getApprovalString(Integer userType , Integer infoType , Integer flag){
        String approvalName = "";
        switch (userType){
            case 0: approvalName = "游客";break;
            case 1: approvalName = "学生";break;
            case 2: approvalName = "教职工";break;
            case 3: approvalName = "校外职员";break;
            case 4: approvalName = "学生亲属";break;
        }
        switch (flag){
            case 0: approvalName += "申请更新";break;
            case 1: approvalName += "申请批量更新";break;
//            case 2: approvalName += "“";break;
        }
        switch (infoType){
            case 0: approvalName += "联系方式";break;
            case 1: approvalName += "地址";break;
            case 2: approvalName += "照片";break;
            case 3: approvalName += "亲属";break;
            case 4: approvalName += "学历";break;
            case 5: approvalName += "简历";break;
            case 6: approvalName += "学生信息";break;
            case 7: approvalName += "教职工信息";break;
            case 8: approvalName += "用户信息";break;
            case 9: approvalName += "学生信息";break;
            case 10: approvalName += "教职工信息";break;
        }
        return approvalName;

    }

}
