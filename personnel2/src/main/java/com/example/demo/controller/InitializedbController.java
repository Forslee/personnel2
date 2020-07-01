package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.service.InitializedbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：初始化数据库
 * DATE:2020/5/15 14:04
 */
@RestController
@CrossOrigin
@RequestMapping("/initializedb")
public class InitializedbController {
    @Autowired
    private InitializedbService initializedbService;

    @RequestMapping(value = "/clear", method = POST ,produces = "application/json;charset=UTF-8")//数据库备份备份初始化
    public Result clear() {
        Result result = new Result();
        try{
            initializedbService.clear_department();
            initializedbService.clear_job();
            initializedbService.clear_personneladjustsalary();
            initializedbService.clear_personnelencourageorpunish();
            initializedbService.clear_operate();
            initializedbService.clear_personnelinfo();
            initializedbService.clear_personnelremove();
            initializedbService.clear_salary();
            initializedbService.clear_op();
            initializedbService.clear_user();
            result.setMsg("初始化数据库成功！！！");
        }catch (Exception e){
            //e.printStackTrace();
            result.setState(false).setMsg("初始化失败！");
        }
        return result;
    }
}
