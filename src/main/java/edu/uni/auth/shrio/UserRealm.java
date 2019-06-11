package edu.uni.auth.shrio;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.PermissionService;
import edu.uni.auth.service.RoleService;
import edu.uni.auth.service.UserServiceOfAuth;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
/**
 * @Author 何亮
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceOfAuth userService;

    /**
     * 获取身份验证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        User user = userService.selectByName(username);
        if(user == null) {
            throw new UnknownAccountException();
        }
        if(Boolean.TRUE.equals(user.getStatus() == User.Status_Locked)) {
            throw new LockedAccountException();
        }

        List<String> roleStrList = userService.selectRoles(user.getId());
        List<String> permissionStrList = userService.selectPermissions(user.getId());

//        System.out.println(Arrays.toString(roleStrList.toArray()));
//        System.out.println(Arrays.toString(permissionStrList.toArray()));
        user.setRoleStrlist(roleStrList);
        user.setPerminsStrlist(permissionStrList);

        return new SimpleAuthenticationInfo(user, user.getPwd(), this.getName());
    }

    /**
     * 根据用户身份获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();   // 这里可以获取身份，就是身份验证时设置的user对象
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRoles(user.getRoleStrlist());
            //用户的权限集合
            info.addStringPermissions(user.getPerminsStrlist());
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }
}
