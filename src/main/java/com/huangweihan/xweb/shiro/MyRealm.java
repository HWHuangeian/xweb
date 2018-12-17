package com.huangweihan.xweb.shiro;

import com.huangweihan.xweb.entity.User;
import com.huangweihan.xweb.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Exrickx
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 返回权限信息
     * subject.isPermitted()时调用
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取用户名
        String username = principal.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if ("hwh".equals(username)) {
            //获得授权角色
            authorizationInfo.addRole("admin");
            //获得授权权限
            authorizationInfo.addStringPermission("admin:manage");
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 执行登录验证
     * subject.login()时调用
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名密码
        String username = token.getPrincipal().toString();
        User user = userService.getUserByUserName(username);
        if (user != null) {
            //得到用户账号和密码存放到authenticationInfo中用于Controller层的权限判断 第三个参数随意不能为null
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
                    user.getUserName());
            return authenticationInfo;
        } else {
            return null;
        }
    }
}
