package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Result;
import com.example.demo.service.OpService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




/**
 * Description：登录
 * DATE:2020/4/23 12:21
 */
@RestController
@CrossOrigin
@Service
@Slf4j
public class LoginController {
    @Autowired
    private OpService opService;

    @PostMapping(value = "/Login",produces = "application/json;charset=UTF-8")
    public Result login(@RequestBody JSONObject jsonObject) throws JSONException {
        String id = null;
        Result result = new Result();

        String userid = jsonObject.getString("userid");
        String password = jsonObject.getString("password");
        String captcha = jsonObject.getString("captcha");
        String sessionCaptcha = (String)SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);

       // System.out.println(sessionCaptcha);

        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            result.setState(false).setMsg("验证码错误");
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(userid,password);
            try {
                subject.login(token);
                result.setMsg("登录成功").setUserid(userid);
            }catch (UnknownAccountException e)
            {
                if(opService.findById(userid)!=null)
                {
                    result.setState(false).setMsg("当前账号已被锁定，请联系系统管理员处理");
                }else{
                    result.setState(false).setMsg("用户不存在");
                }
            }catch (IncorrectCredentialsException e){
                result.setState(false).setMsg("密码错误");
            }
        }
        return result;
    }
}
