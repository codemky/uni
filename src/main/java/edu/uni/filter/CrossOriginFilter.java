package edu.uni.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前后端分离，允许跨域请求的过滤器
 * @Author 何亮
 * @date 2019/4/3
 */
@Component
@WebFilter(urlPatterns= {"/*"}, filterName="crossOriginFilter")
@Order(1)
public class CrossOriginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) sresponse;
		HttpServletRequest request = (HttpServletRequest) srequest;

		response.setContentType("textml;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Allow-Credentials","true"); //是否允许浏览器携带用户身份信息（cookie）
		response.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");

		chain.doFilter(srequest, sresponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}

