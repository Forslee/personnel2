package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.PersonnelInfoService;
import com.example.demo.service.PersonnelencourageorpunishService;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：工资表的增删改查，月末处理（月末结算员工的奖惩分得到奖金）
 * DATE:2020/5/12 10:22
 */
@RestController
@CrossOrigin
@RequestMapping("Salary")
public class SalaryController {
    @Autowired
    private PersonnelInfoService personnelInfoService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private PersonnelencourageorpunishService personnelencourageorpunishService;

    @RequestMapping(value = "/findAll", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Salary>> findAll(){
        List<Salary> salaries = null;
        try{
            salaries = salaryService.findAll();
        }catch (Exception e){
        //    e.printStackTrace();
            return new Result<List<Salary>>(false,"查询所有工资表失败","null",null);
        }
        return new Result<List<Salary>>(true,"查询所有工资表成功","null",salaries);
    }

    /**
     * 查询员工工资表
     * @param personnelid
     * @return
     */
    @RequestMapping(value = "/findone", method = POST ,produces = "application/json;charset=UTF-8")
    public Salary findone(String personnelid){

        return salaryService.findone(personnelid);
    }

    /**
     * 删除员工表
     * @param personnelid
     * @return
     */
    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(String personnelid){
        Result result = new Result();
        try{
            result.setUserid(personnelid);
            salaryService.delete(personnelid);
            result.setMsg("删除信息成功！！！");
        }catch (Exception e){
       //     e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }

    /**
     * 工资表添加
     * @param salary
     * @return
     */
    @RequestMapping(value = "/save", method = POST ,produces = "application/json;charset=UTF-8")
    public Result save(@RequestBody Salary salary){
        Result result = new Result();
        try{
            salaryService.save(salary);
            result.setUserid(salary.getPersonnelid());
            result.setMsg("保存信息成功！！！");
        }catch (Exception e){
       //     e.printStackTrace();
            result.setState(false).setMsg("保存信息失败！");
        }
        return result;
    }

    /**
     * 工资表管理：即修改员工工资表
     * @param salary
     * @return
     */
    @RequestMapping(value = "/update", method = POST ,produces = "application/json;charset=UTF-8")
    public Result update(@RequestBody Salary salary){
        Result result = new Result();
        try{
            Personnelinfo personnelinfo = personnelInfoService.findone(salary.getPersonnelid());
            personnelinfo.setBasissalary(salary.getBasissalary());
            salary.setAllsalary(salary.getBonus()+salary.getTrafficsalary()+salary.getBasissalary());
            salaryService.update(salary);
            personnelInfoService.update(personnelinfo);
            result.setMsg("修改员工工资表成功！！！");
        }catch (Exception e){
         //   e.printStackTrace();
            result.setState(false).setMsg("修改员工工资表失败！");
        }
        return result;
    }

    /**
     * 按部门查询工资表:此工资表为所有工资表，即包括了经过月末结算的工资表也包括未结算的
     * @param department
     * @return
     */
    @RequestMapping(value = "/findsalarybydepartment", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Salary>> salarymanagement(String department){
        List<Salary> salaries = null;
        try{
            salaries = salaryService.findSalaryByDepartment(department);
        }catch (Exception e){
          //  e.printStackTrace();
            return new Result<List<Salary>>(false,"查询工资表失败","null",null);
        }
        return new Result<List<Salary>>(true,"查询所有工资表成功","null",salaries);
    }

    /**
     * 工资表查询：显示经过月末结算的工资表按部门显示
     * @param department
     * @returnHaving_
     */
    @RequestMapping(value = "/findSalary_processing", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Salary>> salary_end_month_management(String department){
        List<Salary> salaries = null;
        try{
            salaries = salaryService.findSalary_Having_processing(department);
        }catch (Exception e){
         //   e.printStackTrace();
            return new Result<List<Salary>>(false,"查询工资表失败","null",null);
        }
        return new Result<List<Salary>>(true,"查询所有工资表成功","null",salaries);
    }

    /**
     * 显示未经过月末结算的工资表按部门显示
     * @param department
     * @return
     */
    @RequestMapping(value = "/findsalary_no_processing", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Salary>> salary_no_month_management(String department){
        List<Salary> salaries = null;
        try{
            salaries = salaryService.findSalary_No_processing(department);
        }catch (Exception e){
            //   e.printStackTrace();
            return new Result<List<Salary>>(false,"查询工资表失败","null",null);
        }
        return new Result<List<Salary>>(true,"查询所有工资表成功","null",salaries);
    }
    /**
     *月末结算：通过这个月的员工对应的奖罚表进行奖罚分的统计，确定最终奖金和这月工资,同时删除这月的所有对应员工的奖罚表
     * @param personnelid
     * @return
     */
    @RequestMapping(value = "/end_month_processing", method = POST ,produces = "application/json;charset=UTF-8")
    public Result end_month_processing(String personnelid){
        Result result = new Result();
        int cardinal = 10; //此为一个月奖金，基本奖金为1000，即10*100;
        List<Personnelencourageorpunish> personnelencourageorpunishes = null;
        try{
           personnelencourageorpunishes = personnelencourageorpunishService.findAll(personnelid);
            int size = personnelencourageorpunishes.size();
            String type =null; //奖惩类型
            int bonus = 0; // 奖金
            for(int i = size-1;i >= 0;i--) {
                type = personnelencourageorpunishes.get(i).getType();
                if (type.equals("001")) {//此为判断奖惩分类型，001奖励，其他为惩罚
                    cardinal = cardinal + personnelencourageorpunishes.get(i).getEncorpunishpoint();
                }else {
                    cardinal = cardinal - personnelencourageorpunishes.get(i).getEncorpunishpoint();
                }
            }
            if(cardinal<=0){
                bonus = 100;//当基本奖金扣完后，给予最少奖金 100
            }else {
                bonus = cardinal*100;
            }
            Salary salary = salaryService.findone(personnelid);
            salary.setBonus(bonus);
            salary.setAllsalary(salary.getAllsalary()+salary.getBonus());
            salaryService.update(salary);
            personnelencourageorpunishService.delete(personnelid);
            result.setMsg("操作成功！");
        }catch (Exception e){
         //   e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }

}
