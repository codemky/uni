package edu.uni.auth.service.impl;

import edu.uni.auth.bean.*;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 何亮
 */
public class AuthServiceImpl implements AuthService {
    @Autowired
    private RoleMapper roleMapper;


    /**
     * 在对角色进行增删改操作後，需要调用此方法进行动态刷新
     * @param shiroFilterFactoryBean
     */
    @Override
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Map<String, String> init) {
        synchronized (this) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("从shiroFilterFactory获取ShiroFilter失败");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(generateFilterChain(init));
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }

    /**
     * <p>
     *     生成权限过滤链
     * </p>
     * @return
     */
    @Override
    public Map<String, String> generateFilterChain(Map<String, String> init){
        Map<String, String> filterChain = new LinkedHashMap<>();
        if(init != null && init.size() > 0 ){
            init.forEach((k,v) ->{
                filterChain.put(k, v);
            });
        }
        before(filterChain);
        custom(filterChain);
        after(filterChain);
        return filterChain;
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @Override
    public User getUser(){
        // 获取登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user;
    }


    private void before(Map<String, String> filterChain){

        filterChain.put("json/example/getLoginInfo", "anon");
        filterChain.put("/json/auth/index", "anon");
        filterChain.put("/json/auth/update", "anon");
        filterChain.put("/json/auth/403", "anon");
        filterChain.put("/json/auth/login**", "anon");
        filterChain.put("/json/auth/checkImg**", "anon");
        filterChain.put("/json/auth/user/checkNameExist**", "anon");
        filterChain.put("/json/auth/user/changePwd", "authc");
        filterChain.put("/json/auth/logout", "authc");
        filterChain.put("/json/auth/menu", "authc");

    }

    private void after(Map<String, String> filterChain){

        // 运维者权限
        filterChain.put("/json/auth/uisup**", "restfulFilter[POST(Operator),DELETE(Operator),PUT(Operator),GET(Operator)]");
        filterChain.put("/json/auth/unisup**", "restfulFilter[POST(Operator),DELETE(Operator),PUT(Operator),GET(Operator)]");
        filterChain.put("/json/auth/uisups/**", "restfulFilter[POST(Operator),DELETE(Operator),PUT(Operator),GET(Operator)]");
        filterChain.put("/json/auth/universitys/*", "restfulFilter[POST(Operator),DELETE(Operator),PUT(Operator),GET(Operator)]");
        filterChain.put("/json/auth/universitys/*/*", "restfulFilter[POST(Operator),DELETE(Operator),PUT(Operator),GET(Operator)]");

        filterChain.put("/json/auth/user/listLikeUnivIdNameEname/*/*/*", "restfulFilter[GET(Operator/UnivInfoSUP)]");
        filterChain.put("/json/auth/user/listLikeUnivNameEname/*/*", "restfulFilter[GET(UnivInfoSUP)]");

        // 学校信息管理员权限
        filterChain.put("/json/auth/**", "restfulFilter[POST(UnivInfoSUP),DELETE(UnivInfoSUP),PUT(UnivInfoSUP),GET(UnivInfoSUP)]");

        // 权宜之计，让其他任何接口都需要登录才能进行操作
        filterChain.put("/**",  "anon");
    }

    /**
     * 动态权限生成
     * @param filterChain
     */
    private void custom(Map<String, String> filterChain){
//        filterChain.put("/user", "restfulFilter[GET(user/admin),DELETE(admin),POST(admin)]");
//        filterChain.put("/admin", "restfulFilter[GET(admin)]");
        List<Url> urls = roleMapper.selectUrl();
        HashMap<String, RestfulRoles> restfulUrlHashMap = new HashMap<>();
        urls.forEach(url -> {
            System.out.println(url);
            RestfulRoles restfulRoles = restfulUrlHashMap.get(url.getUrl());
            if(restfulRoles == null){
                restfulRoles = new RestfulRoles();
                List<String> roles = new LinkedList<>();
                roles.add(url.getName());
                if("POST".equalsIgnoreCase(url.getMethod())){
                    restfulRoles.setPostRoles(roles);
                }else if("DELETE".equalsIgnoreCase(url.getMethod())){
                    restfulRoles.setDeleteRoles(roles);
                }else if("PUT".equalsIgnoreCase(url.getMethod())){
                    restfulRoles.setPutRoles(roles);
                }else if("GET".equalsIgnoreCase(url.getMethod())){
                    restfulRoles.setGetRoles(roles);
                }
                restfulUrlHashMap.put(url.getUrl(), restfulRoles);
            }else {
                if("POST".equalsIgnoreCase(url.getMethod())){
                    List<String> postRoles = restfulRoles.getPostRoles();
                    if(postRoles == null){
                        postRoles = new LinkedList<>();
                        postRoles.add(url.getName());
                        restfulRoles.setPostRoles(postRoles);
                    }else{
                        postRoles.add(url.getName());
                    }
                }else if("DELETE".equalsIgnoreCase(url.getMethod())){
                    List<String> deleteRoles = restfulRoles.getDeleteRoles();
                    if(deleteRoles == null){
                        deleteRoles = new LinkedList<>();
                        deleteRoles.add(url.getName());
                        restfulRoles.setDeleteRoles(deleteRoles);
                    }else{
                        deleteRoles.add(url.getName());
                    }
                }else if("PUT".equalsIgnoreCase(url.getMethod())){
                    List<String> putRoles = restfulRoles.getPutRoles();
                    if(putRoles == null){
                        putRoles = new LinkedList<>();
                        putRoles.add(url.getName());
                        restfulRoles.setPutRoles(putRoles);
                    }else{
                        putRoles.add(url.getName());
                    }
                }else if("GET".equalsIgnoreCase(url.getMethod())){
                    List<String> getRoles = restfulRoles.getGetRoles();
                    if(getRoles == null){
                        getRoles = new LinkedList<>();
                        getRoles.add(url.getName());
                        restfulRoles.setGetRoles(getRoles);
                    }else{
                        getRoles.add(url.getName());
                    }
                }
            }
        });

        restfulUrlHashMap.forEach((k,v) ->{
            System.out.println(k + "——" + v.generateRestfulPermission());
            filterChain.put(k,v.generateRestfulPermission());
        });
    }
}
