package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：操作员的增删改查
 * DATE:2020/5/14 11:56
 */
@RestController
@CrossOrigin
@RequestMapping("/op")//改模块只 对系统管理员开放
public class OpController {
    @Autowired
    private OpService opService;
    @Autowired
    private PersonneladjustsalaryService personneladjustsalaryService;
    @Autowired
    private OperateService operateService;
    @Autowired
    private PersonnelremoveService personnelremoveService;
    @Autowired
    private PersonnelencourageorpunishService personnelencourageorpunishService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/findAll", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Op>> findAll(){
        List<Op> ops;
        try{
           ops = opService.findAll();
        }catch (Exception e){
            // e.printStackTrace();
            return new Result<List<Op>>(false,"查询所有操作员失败","null",null);
        }
        //System.out.println(jobService.findall().toString());
        return new Result<List<Op>>(true,"查询所有操作员成功","null",ops);
    }

    @RequestMapping(value = "/findone", method = POST ,produces = "application/json;charset=UTF-8")
    public Op findone(String id){
        return opService.findById(id);
    }

    @RequestMapping(value = "/save", method = POST ,produces = "application/json;charset=UTF-8")
    public Result save(@RequestBody Op op){
        Result result = new Result();
        try{
            opService.add(op);
            User user = new User();
            user.setUserId(op.getOpid());
            user.setUserName(op.getOpname());
            user.setUserPassword(op.getOppassword());
            user.setUserType("002");
            userService.add(user);
            result.setUserid(op.getOpid());
            result.setMsg("保存信息成功！！！");
        }catch (Exception e){
          //  e.printStackTrace();
            result.setState(false).setMsg("保存信息失败！");
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(String opid){
        Result result = new Result();
        try{

            personneladjustsalaryService.deletebyop(opid);
            personnelencourageorpunishService.deletebyop(opid);
            personnelremoveService.deletebyop(opid);
            operateService.delete(opid);
            opService.delete(opid);
            userService.delete(opid);
            result.setMsg("删除信息成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }

    @RequestMapping(value = "/update", method = POST ,produces = "application/json;charset=UTF-8")
    public Result update(String opid){
        Result result = new Result();
        Op op = opService.findById(opid);

        try{
            if(op.getPermission().equals("0")) {//指当前操作员是否为禁用状态
                op.setPermission("1");//指系统管理员启用该操作员
            }else {
                op.setPermission("0");//指系统管理员禁用该操作员
            }
            opService.update(op);
            result.setMsg("禁用操作员成功！！！");
        }catch (Exception e){
           // e.printStackTrace();
            result.setState(false).setMsg("禁用操作员失败！");
        }
        return result;
    }
}
