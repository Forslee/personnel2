package com.example.demo.controller;

import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Personnelremove;
import com.example.demo.entity.Result;
import com.example.demo.entity.Salary;
import com.example.demo.service.PersonnelInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：人事信息统计:可根据性别工资职称等查询员工信息
 * DATE:2020/5/13 21:05
 */
@RestController
@CrossOrigin
@Service
@Slf4j
@RequestMapping("/personnel_information_statistics")
public class Personnel_Information_Statistics_Controller {
    @Autowired
    private PersonnelInfoService personnelInfoService;

    @RequestMapping(value = "/findonebysex", method = POST ,produces = "application/json;charset=UTF-8")
     public Result<List<Personnelinfo>> findonebysex(String sex){
        List<Personnelinfo> personnelinfos = null;
        try{
            personnelinfos = personnelInfoService.findbysex(sex);
        }catch (Exception e){
        //    e.printStackTrace();
            return new Result<List<Personnelinfo>>(false,"查询员工信息失败","null",null);
        }
        return new Result<List<Personnelinfo>>(true,"查询员工信息成功","null",personnelinfos);
    }

    @RequestMapping(value = "/findonebysalary", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personnelinfo>> findonebysalary(Integer salary){
        List<Personnelinfo> personnelinfos = null;
        try{
            personnelinfos = personnelInfoService.findbysalary(salary);
        }catch (Exception e){
         //   e.printStackTrace();
            return new Result<List<Personnelinfo>>(false,"查询员工信息失败","null",null);
        }
        return new Result<List<Personnelinfo>>(true,"查询员工信息成功","null",personnelinfos);
    }

    @RequestMapping(value = "/findonebyjob", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personnelinfo>> findonebysalary(String job){
        List<Personnelinfo> personnelinfos = null;
        try{
            personnelinfos = personnelInfoService.findbyjob(job);
        }catch (Exception e){
          //  e.printStackTrace();
            return new Result<List<Personnelinfo>>(false,"查询员工信息失败","null",null);
        }
        return new Result<List<Personnelinfo>>(true,"查询员工信息成功","null",personnelinfos);
    }
}
