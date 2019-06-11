package edu.uni.utils;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * 常用工具类的封装，md5,uuid等
 * @Author 何亮
 * @date 2019/4/3
 */
public class CommonUtils {

    /**
     * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
     * @return
     */
    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString().
                replaceAll("-","").substring(0,32);
        return uuid;
    }

    /**
     * md5常用工具类
     * @param data
     * @return
     */
    public static String MD5(String data){
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 設置跨域請求頭
     * @param request
     * @param response
     */
    public static void setCrossingHeader(HttpServletRequest request, HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setContentType("textml;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}
