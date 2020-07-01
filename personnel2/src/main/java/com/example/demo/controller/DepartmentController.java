package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Result;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：部门的增删查
 * DATE:2020/5/12 11:33
 */
@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PersonnelInfoService personnelInfoService;
    @Autowired
    private PersonneladjustsalaryService personneladjustsalaryService;
    @Autowired
    private PersonnelremoveService personnelremoveService;
    @Autowired
    private PersonnelencourageorpunishService personnelencourageorpunishService;
    @Autowired
    private SalaryService salaryService;

    @RequestMapping(value = "/findAll", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Department>> findAll(){
        List<Department> departments = null;
        try{
          departments = departmentService.findall();
        }catch (Exception e){
            e.printStackTrace();
            return new Result<List<Department>>(false,"查询所有部门失败","null",null);
        }

        return new Result<List<Department>>(true,"查询所有部门成功","null",departments);
    }

    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(String departmentname){
        Result result = new Result();
        try{
            result.setUserid(departmentname);
            /**
             * 删除相关所有员工信息
             */
            List<Personnelinfo> list = new ArrayList<>();
            String id = null;
            list = personnelInfoService.findbydepartment(departmentname);
            int size = list.size();
            for(int i = size-1;i >= 0;i--){
                id = list.get(i).getPersonnelid();
                salaryService.delete(id);
                personneladjustsalaryService.delete(id);
                personnelencourageorpunishService.delete(id);
                personnelremoveService.delete(id);
            }
            personnelInfoService.deletebydepartment(departmentname);
            departmentService.delete(departmentname);
            result.setMsg("删除部门成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("删除部门失败！");
        }
        return result;
    }
    @RequestMapping(value = "/save", method = POST ,produces = "application/json;charset=UTF-8")
    public Result save(@RequestBody Department department){
        Result result = new Result();
        try{
            departmentService.add(department);
            result.setUserid(department.getDepartmentId());
            result.setMsg("保存部门成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("保存部门失败！");
        }
        return result;
    }
}
