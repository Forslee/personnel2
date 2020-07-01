package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.PersonnelInfoService;
import com.example.demo.service.PersonneladjustsalaryService;
import com.example.demo.service.SalaryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：员工调薪
 * DATE:2020/4/28 10:47
 */
@RestController
@CrossOrigin
@Service
@Slf4j
@RequestMapping("/adjustsalary")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//先试试不知道行不行
public class PersonneladjustsalaryController {
    @Autowired
    private PersonneladjustsalaryService personneladjustsalaryService;
    @Autowired
    private PersonnelInfoService personnelInfoService;
    @Autowired
    private SalaryService salaryService;
    //private Date time;


    @RequestMapping(value = "/findAll", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personneladjustsalary>> findAll(){
        List<Personneladjustsalary> personneladjustsalaries = null;
        try{
            personneladjustsalaries = personneladjustsalaryService.findAll();
        }catch (Exception e){
          //  e.printStackTrace();
            return new Result<List<Personneladjustsalary>>(false,"查询所有调薪表失败","null",null);
        }
        return new Result<List<Personneladjustsalary>>(true,"查询所有调薪表成功","null",personneladjustsalaries);
    }

    /**
     * 调整员工薪水，增加薪水调整表
     * @param personneladjustsalary
     * @return
     */
    @RequestMapping(value = "/update", method = POST ,produces = "application/json;charset=UTF-8")
    public Result update(@RequestBody Personneladjustsalary personneladjustsalary) {
        Result result = new Result();
        String id = personneladjustsalary.getPersonnelid();
        Personnelinfo personnelinfo = personnelInfoService.findone(id);
        Salary salary = salaryService.findone(id);
        if (personnelinfo == null) {
            result.setMsg("操作失败，没有该用户！！！");
        } else {
            try{
                personneladjustsalary.setBeforeadjustsalary(personnelinfo.getBasissalary());
                personneladjustsalaryService.add(personneladjustsalary);
                salary.setBasissalary(personneladjustsalary.getAfteradjustsalary());
                salary.setAllsalary(salary.getBasissalary()+salary.getTrafficsalary()+salary.getBonus());
                salaryService.update(salary);
                personnelinfo.setBasissalary(personneladjustsalary.getAfteradjustsalary());
                personnelInfoService.update(personnelinfo);
                result.setMsg("完成操作！！！");
            }catch (Exception e){
                result.setState(false).setMsg("操作失败！");
            }
        }
        return result;
    }

    /**
     * 删除该员工的所有调薪表
     * @param personnelid
     * @return
     */
    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(  String personnelid){
          Result result = new Result();
        try{
            result.setUserid(personnelid);
            personneladjustsalaryService.delete(personnelid);
            result.setMsg("该员工的调薪表删除信息成功！！！");
        }catch (Exception e){
           // e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }

    @RequestMapping(value = "/deletebyno", method = POST ,produces = "application/json;charset=UTF-8")
    public Result deletebyno(  Integer no){
        Result result = new Result();
        try{
            personneladjustsalaryService.deletebyno(no);
            result.setMsg("调薪表删除信息成功！！！");
        }catch (Exception e){
            // e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }
    @RequestMapping(value = "/findbyid", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personneladjustsalary>> findbyid(String id){
        List<Personneladjustsalary> personneladjustsalaries = null;
        try{
            personneladjustsalaries = personneladjustsalaryService.findAllbyid(id);
        }catch (Exception e){
            //  e.printStackTrace();
            return new Result<List<Personneladjustsalary>>(false,"查询所有调薪表失败","null",null);
        }
        return new Result<List<Personneladjustsalary>>(true,"查询所有调薪表成功","null",personneladjustsalaries);
    }
    /**
     * 查看最新的员工调薪表
     * @param personnelid
     * @return
     */
    @RequestMapping(value = "/find_new_one", method = POST ,produces = "application/json;charset=UTF-8")
    public Personneladjustsalary find_new_one(String personnelid){
        return personneladjustsalaryService.find_new_one(personnelid);
    }
}
