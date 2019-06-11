package edu.uni.auth.shrio;
import edu.uni.auth.bean.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author 何亮
 * 自定义密码校验策略
 */
public class CustomCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        PrincipalCollection principals = info.getPrincipals();
        User user = (User)principals.getPrimaryPrincipal();
        String password = encodePassword(new String(usernamePasswordToken.getPassword()), user.getSalt());
        String dbPassword = (String) info.getCredentials();
        return this.equals(password, dbPassword);
    }

    public static String encodePassword(String pswd, String salt){
        String newPwsd = new Md5Hash(pswd, salt).toString();
        return newPwsd;
    }
}
