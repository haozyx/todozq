package com.bootdo.system.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootdo.system.filter.LogoutFilter;
import com.bootdo.system.filter.RestfulFilter;
import com.bootdo.system.shiro.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;

/**
 * @author wanghby@yonyou.com
 */
@Configuration
public class ShiroConfig {
 

    @Value("${spring.cache.type}")
    private String cacheType ;

    @Value("${server.session-timeout}")
    private int tomcatTimeout;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);  //设置安全管理器 相当于注入了securityManager
        shiroFilterFactoryBean.setLoginUrl("/youcandoit");  //登录界面
        shiroFilterFactoryBean.setSuccessUrl("/nodoornoway"); //登录成功之后跳转界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");// 未授权界面
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/baidu_verify_UjgmuF5wfc.html","anon");  // 过滤链定义，从上到下顺序执行。/**放在最下面，anon 都可以访问，authc 授权才能访问
        filterChainDefinitionMap.put("/youcandoit","anon");  // 过滤链定义，从上到下顺序执行。/**放在最下面，anon 都可以访问，authc 授权才能访问
        filterChainDefinitionMap.put("/truelogin","anon");  // 过滤链定义，从上到下顺序执行。/**放在最下面，anon 都可以访问，authc 授权才能访问
        filterChainDefinitionMap.put("/getVerify","anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        
        filterChainDefinitionMap.put("/m/**", "anon");//手机端所有的API
        
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/zy/**", "anon");
        filterChainDefinitionMap.put("/zy/page/**", "anon");
        filterChainDefinitionMap.put("/zy", "anon"); //竟然是两个地址
        filterChainDefinitionMap.put("/zy/post/**", "anon");
        filterChainDefinitionMap.put("/zy/index/**", "anon");
        filterChainDefinitionMap.put("/nodoornoway", "authc");
        filterChainDefinitionMap.put("/adminm/**", "authc");
        
        LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/youcandoit");
        
        RestfulFilter restfulFilter = new RestfulFilter();

		shiroFilterFactoryBean.getFilters().put("authc", restfulFilter);
		shiroFilterFactoryBean.getFilters().put("logout", logoutFilter);
		
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager() { //bean = securityManager的定义
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(userRealm()); //用户真正的身份认证
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     	* 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。因为在Shiro中，
     	* 最终是通过Realm来获取应用程序中的用户、角色及权限信息的。通常情况下，在Realm中会直接从我们
     	* 的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.
     	* 需要自己实现（关键点）
     * @return
     */
    @Bean
    UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

 

    @Bean
    public SessionDAO sessionDAO() {
    	 return new MemorySessionDAO();
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO());
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(cacheManager());
        return em;
    }

    @Bean("cacheManager2")
    CacheManager cacheManager(){
        return CacheManager.create();
    }


}
