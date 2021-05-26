package com.tang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.tang.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm realm;
    @Bean
    public DefaultWebSecurityManager securityManager(){

        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();


//        获取hash加密对象
        HashedCredentialsMatcher has = new HashedCredentialsMatcher();
//        设置加密算法md5
        has.setHashAlgorithmName("md5");
//        设置迭代加密2次
        has.setHashIterations(2);
        realm.setCredentialsMatcher(has);
        defaultSecurityManager.setRealm(realm);
        defaultSecurityManager.setRememberMeManager(rememberMeManager());
        defaultSecurityManager.setCacheManager(ehCacheManager());
        return defaultSecurityManager;


    }


    public SimpleCookie rememberMeCookie()
    {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
//        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    /**
     * 记住我
     */
    public CookieRememberMeManager rememberMeManager()
    {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("MTIzNDU2NzgxMjM0NTY3OA=="));
        return cookieRememberMeManager;
    }

    /*
        shiro自定义过滤器
     */
    @Bean
    public ShiroFilterChainDefinition definition(){
        DefaultShiroFilterChainDefinition di = new DefaultShiroFilterChainDefinition();

        di.addPathDefinition("/index","anon");
        di.addPathDefinition("/main","anon");
        di.addPathDefinition("/login","anon");
        di.addPathDefinition("/logout","logout");
        di.addPathDefinition("/setone","anon");

        di.addPathDefinition("/erro","anon");
        di.addPathDefinition("/**","authc");

        return di;

    }

    @Bean
    public ShiroDialect shiroDialect(){

        return new ShiroDialect();
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        InputStream is = null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        net.sf.ehcache.CacheManager cacheManager = new net.sf.ehcache.CacheManager(is);
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

}
