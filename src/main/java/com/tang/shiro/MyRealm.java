package com.tang.shiro;

import com.tang.pojo.User;
import com.tang.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*

自定义规则
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /*
    自定义授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("我被执行了");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        info.addStringPermission("ms");
        return info;
    }
    /*
        自定义验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取传递过来的身份
        Object principal = authenticationToken.getPrincipal();
        User user = userService.selAll((String)principal);
        if(user!=null){

            //校验凭证
            //5b5d01609b34664aafefc4bde5f0892f
            AuthenticationInfo info =  new SimpleAuthenticationInfo(principal,user.getPassword(), ByteSource.Util.bytes(user.getId()+""),user.getLogin());
        return info;
        }



        return null;
    }
}
