package com.example.demo.controller;

import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Result;
import com.example.demo.entity.Salary;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：员工的增删改查
 * DATE:2020/4/23 21:09
 */
@RestController
@CrossOrigin
@RequestMapping("/personnelinfo")
public class PersonnelInfoController {
    @Autowired
    private PersonnelInfoService personnelInfoService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private PersonnelencourageorpunishService personnelencourageorpunishService;
    @Autowired
    private PersonnelremoveService personnelremoveService;
    @Autowired
    private PersonneladjustsalaryService personneladjustsalaryService;
    /**
     * 修改员工信息
     * @param personnelinfo
     * @return
     */
    @RequestMapping(value = "/update", method = POST ,produces = "application/json;charset=UTF-8")
    public Result update(@RequestBody Personnelinfo personnelinfo){
        Result result = new Result();
        try{
            personnelInfoService.update(personnelinfo);
            result.setMsg("修改员工信息成功！！！");
        }catch (Exception e){
            result.setState(false).setMsg("修改员工信息失败！");
        }
        return result;
    }

    /**
     * 查询单个员工信息
     * @param personnelid
     * @return
     */

    @RequestMapping(value = "/findone", method = POST ,produces = "application/json;charset=UTF-8")

    public Personnelinfo findone(String personnelid){
           // System.out.println(personnelInfoService.findone(personnelid).geteMail()+"---"+personnelInfoService.findone(personnelid).getBasissalary());
        return personnelInfoService.findone(personnelid);
    }

    /**
     * 删除员工信息
     * @param personnelid
     * @return
     */
    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(  String personnelid){
        Result result = new Result();
        try{
            result.setUserid(personnelid);
            /**
             * 同时删除员工的其他信息
             */
            personneladjustsalaryService.delete(personnelid);
            salaryService.delete(personnelid);
            personnelencourageorpunishService.delete(personnelid);
            personnelremoveService.delete(personnelid);
            personnelInfoService.delete(personnelid);
            result.setMsg("删除信息成功！！！");
        }catch (Exception e){
           // e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }



    /**
     * 保存员工信息
     * @param personnelinfo
     * @return
     */
    @RequestMapping(value = "/save", method = POST ,produces = "application/json;charset=UTF-8")
    public Result save(@RequestBody Personnelinfo personnelinfo){
        Result result = new Result();
        try{
            if(personnelinfo.getPersonnelid()!=null){
                if (personnelInfoService.findone(personnelinfo.getPersonnelid())==null)
                {
                    personnelInfoService.save(personnelinfo);
                    Salary salary = new Salary();
                    salary.setPersonnelid(personnelinfo.getPersonnelid());
                    salary.setPersonnelname(personnelinfo.getPersonnelname());
                    salary.setBasissalary(personnelinfo.getBasissalary());
                    salary.setDepartment(personnelinfo.getDepartment());
                    salary.setBonus(0);
                    salary.setTrafficsalary(500);
                    if(personnelinfo.getBasissalary()!=null)
                        salary.setAllsalary(personnelinfo.getBasissalary()+500);
                    else
                        salary.setAllsalary(500);
                    salaryService.save(salary);
                    result.setMsg("保存信息成功！！！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("保存信息失败！");
        }
        return result;
    }
    /**
     * 查询所有并分页
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/findByPage", method = POST ,produces = "application/json;charset=UTF-8")
    public Map<String,Object> findByPage(Integer page, Integer rows){
        page = page==null?1:page;
        rows = rows==null?5:rows;
        HashMap<String,Object> map = new HashMap<>();
        //分页处理
        List<Personnelinfo> personnelinfos = personnelInfoService.findByPage(page,rows);
        //计算总页数
        Integer totals = personnelInfoService.findTotals();
        Integer totalpage = totals%rows==0?totals/rows:totals/rows+1;
        map.put("personnelinfos",personnelinfos);
        map.put("totals",totals);
        map.put("totlpage",totalpage);
        map.put("page",page);
        return map;
    }
}
