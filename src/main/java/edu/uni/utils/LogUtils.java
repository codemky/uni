package edu.uni.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用日志工具类
 * @Author 何亮
 * @date 2019/4/3
 */
public class LogUtils {
	private Logger logger;
	
	public LogUtils(Class c) {
		logger = LoggerFactory.getLogger(c);
	}
    public void logException(Throwable e){
    	StringBuffer sb = new StringBuffer("# * # * # * # * # * # * # * # * # * # * [LogUtils]\n");
    	try {
    		StackTraceElement s= e.getStackTrace()[0];
			sb.append("\n报错的文件是：\n\t"+s.getFileName());
			sb.append("\n报错方法是：\n\t"+s.getMethodName());
			sb.append("\n报错的行是：\n\t"+ s.getLineNumber());
        	sb.append("\n报错信息是：\n\t" + e.getMessage());
    	}catch (Exception e2) {
    		sb.append("\n【日志后续处理出错】");
		}
    	logger.error(sb.toString());
    }
    public void error(String msg) {
    	logger.error(msg);
    }
    public void warn(String msg) {
    	logger.warn(msg);
    }
    public void info(String msg) {
    	logger.info(msg);
    }
    public void debug(String msg) {
    	logger.debug(msg);
    }
}
