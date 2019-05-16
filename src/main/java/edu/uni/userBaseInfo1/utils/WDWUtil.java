package edu.uni.userBaseInfo1.utils;

/**
 * @Author chenenru
 * @ClassName WDWUtil
 * @Description
 * @Date 2019/5/14 19:49
 * @Version 1.0
 **/

/**

 * 判断Excel 文件的版本

 * Created by Administrator on 2018/7/4.

 */

public class WDWUtil {

    // @描述：是否是2003的excel，返回true是2003

    public static boolean isExcel2003(String filePath) {

        return filePath.matches("^.+\\.(?i)(xls)$");

    }



    //@描述：是否是2007的excel，返回true是2007

    public static boolean isExcel2007(String filePath) {

        return filePath.matches("^.+\\.(?i)(xlsx)$");

    }

}
