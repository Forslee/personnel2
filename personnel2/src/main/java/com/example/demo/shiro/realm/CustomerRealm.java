package com.example.demo.shiro.realm;

import com.example.demo.entity.Op;
import com.example.demo.entity.Sys;
import com.example.demo.entity.User;
import com.example.demo.service.OpService;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Description：
 * DATE:2020/4/24 16:34
 */
@Service
public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    OpService opService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       // System.out.println("执行了=>授权doGetAuthenticationInfo");

        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色和设置当前对象的权限
        if(currentUser.getUserType().equals("001")){   //即该用户为系统管理员
             info.addRole("admin");  //系统管理员权限
        }else{                                         //即该用户为操作员
            info.addRole("normal");  //操作员权限

        }
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       // System.out.println("执行了=>认证doGetAuthenticationInfo");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.findById(usernamePasswordToken.getUsername());

        if(user==null){ return null; }
        if(user.getUserType().equals("002")){ //当前用户为操作员
            Op op = opService.findById(user.getUserId());
            if (op.getPermission().equals("0")) {//当前用户被系统管理员禁用
                return  null;
            }
        }
        return new SimpleAuthenticationInfo(user,user.getUserPassword(),"");

    }
}
