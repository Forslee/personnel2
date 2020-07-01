package com.example.demo.shiro.config;

import com.example.demo.shiro.realm.CustomerRealm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**定义shiro的配置类
 * Description：
 * DATE:2020/4/24 15:00
 */
@Configuration

@Mapper
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //自定义认证页面
        //filterFactoryBean.setLoginUrl("");
        //添加shiro的内置过滤器
        /**
         * anon 无需认证就能访问
         * authc 必须认证才能访问
         * user 必须拥有 记住我 功能才能用
         * permission 拥有对某个资源的权限才能访问
         */
        //拦截
        //配置资源受限表
        Map<String, String> map = new HashMap<String, String>();

        //放行登录界面

        //拦截所有资源
        map.put("/login","anon");
        //  map.put("/**","authc");

        map.put("/Captcha.jpg","anon");
        filterFactoryBean.setFilterChainDefinitionMap(map);

        //设置登录请求
        filterFactoryBean.setLoginUrl("/Login");
        //未授权
        filterFactoryBean.setUnauthorizedUrl("/noauth");
        return filterFactoryBean;
    }
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("customerRealm") CustomerRealm customerRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //customerRealm
        securityManager.setRealm(customerRealm);
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean类
     * @return
     */




    //创建realm对象
    @Bean
    public CustomerRealm customerRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //customerRealm.setCredentialsMatcher(getCredentialsMatcher());
        return customerRealm;
    }

    // 创建凭证匹配器
    @Bean
    public CredentialsMatcher getCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //注入散列算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(100);
        return hashedCredentialsMatcher;
    }


    //整合shiroDialect: 用来整合shiro thymeleaf
   // @Bean
    //public ShiroDialect getShiroDialect(){
     //   return new ShiroDialect();
    //}
}
