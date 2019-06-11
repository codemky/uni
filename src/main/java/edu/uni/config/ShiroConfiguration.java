package edu.uni.config;

import edu.uni.auth.service.AuthService;
import edu.uni.auth.service.impl.AuthServiceImpl;
import edu.uni.auth.shrio.CustomCredentialMatcher;
import edu.uni.auth.shrio.RestfulRolesFilter;
import edu.uni.auth.shrio.UserRealm;
import edu.uni.filter.ShiroUserFilter;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author 何亮
 * @date 2019/4/3
 */
@Configuration
public class ShiroConfiguration {

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("customCredentialMatcher")
    public CustomCredentialMatcher credentialMatcher() {
        return new CustomCredentialMatcher();
    }

    @Bean(name = "userRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public UserRealm userRealm(@Qualifier("customCredentialMatcher") CustomCredentialMatcher matcher) {
        UserRealm realm = new UserRealm();
        // 设置密码匹配策略
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm,
                                                     @Qualifier("ehCacheManager") EhCacheManager ehCacheManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        ModularRealmAuthenticator authenticator = (ModularRealmAuthenticator) securityManager.getAuthenticator();
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setRealm(userRealm);

        ServletContainerSessionManager sessionManager = (ServletContainerSessionManager) securityManager.getSessionManager();

        securityManager.setCacheManager(ehCacheManager);//

        return securityManager;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean(name="authService")
    public AuthService authService(){
        return new AuthServiceImpl();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                         @Qualifier("authService") AuthService authService){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("restfulFilter", new RestfulRolesFilter());
        filtersMap.put("authc", new ShiroUserFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        Map<String, String> filterChainDefinitionManager = authService.generateFilterChain(null);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        // 不设置的话，在未授权情况下默认跳转到login.jsp
        shiroFilterFactoryBean.setLoginUrl("/json/auth/403");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/json/auth/success");
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/json/auth/403");

        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * 配置我们使用的securityManager
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

}