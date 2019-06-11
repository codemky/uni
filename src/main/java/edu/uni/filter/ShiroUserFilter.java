package edu.uni.filter;

import edu.uni.utils.CommonUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写shiro的UserFilter，实现通过OPTIONS方式的预请求
 * @author 何亮
 */
public class ShiroUserFilter extends UserFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            CommonUtils.setCrossingHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request,response);
    }


}