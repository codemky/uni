package edu.uni.auth.shrio;

import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.utils.CommonUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * 自定义校验策略
 * @Author 何亮
 */
public class RestfulRolesFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpResponse.setStatus(HttpStatus.OK.value());
//            CommonUtils.setCrossingHeader(httpRequest,httpResponse);
            return true;
        }

        Subject subject = getSubject(request, response);

        //配置的访问所需角色
        String[] methodsArray = (String[]) mappedValue;
        for (int i=0 ; i<methodsArray.length ; i++){
//            System.out.println(methodsArray[i]);
        }
        if (methodsArray == null || methodsArray.length == 0) {
            return true;
        }
        String method = ((HttpServletRequest) request).getMethod();
        // 根据请求方式，再查看用户是否包含该角色的权限
        for(int i=0 ; i<methodsArray.length ; ++i){
            if(methodsArray[i].startsWith(method)){
//                System.out.println("is" + method);
                String[] roleArray = methodsArray[i].substring(method.length() + 1, methodsArray[i].length() - 1).split("/");
//                System.out.println(Arrays.toString(roleArray));
                List<String> roles = CollectionUtils.asList(roleArray);
                boolean[] bo = subject.hasRoles(roles);
                //如果有一个角色满足，则可以访问
                for(boolean b : bo){
                    if(b == true){
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

}