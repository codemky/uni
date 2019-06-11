package edu.uni.auth.service;

import edu.uni.auth.bean.User;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.Map;

/**
 * 自定义shiro权限服务
 * @author 何亮
 */
public interface AuthService {
    /**
     * 生成shrio权限过滤链
     * @return
     */
    Map<String, String> generateFilterChain(Map<String, String> init);
    // 获取当前登录的用户
    User getUser();

    /**
     * 动态权限更新
     * @param shiroFilterFactoryBean
     */
    void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Map<String, String> init);

}
