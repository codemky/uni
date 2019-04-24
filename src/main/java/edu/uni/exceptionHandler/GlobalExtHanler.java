package edu.uni.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.utils.LogUtils;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExtHanler {
	private LogUtils logUilts = new LogUtils(this.getClass());
	/*
	 * 全局异常处理器
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result handlerException(Exception e, HttpServletRequest request) {
		logUilts.logException(e);
		return Result.build(ResultType.Exception, e.getMessage()).appendData("Exception", e.getClass().getName());
	}
}
