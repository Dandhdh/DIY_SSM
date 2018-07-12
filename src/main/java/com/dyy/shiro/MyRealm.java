package com.dyy.shiro;

import com.dyy.pojo.T_user;
import com.dyy.service.T_userService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * shiro自定义域
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private T_userService t_userService;

    /**
     * 用于的权限的认证。（先登陆成功才有权限认证）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取到当前登陆用户
        String username = principalCollection.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        // 查询到用户role和permissions
        Set<String> roleName = t_userService.findRoles(username) ;
        Set<String> permissions = t_userService.findPermissions(username) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 首先执行这个登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户账号
        String username = token.getPrincipal().toString() ;
        T_user user = t_userService.findUserByUsername(username) ;
        if (user != null){
            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),
                    "a") ;
            return authenticationInfo ;
        }else{
            return  null ;
        }
    }
}
