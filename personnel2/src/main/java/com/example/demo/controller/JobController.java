package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：职位的增删查：
 * DATE:2020/5/12 11:26
 */
@RestController
@CrossOrigin
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;
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
    public Result<List<Job>> findAll(){
        List<Job> jobs;
        try{
            jobs = jobService.findall();
        }catch (Exception e){
           // e.printStackTrace();
            return new Result<List<Job>>(false,"查询所有职位失败","null",null);
        }
        //System.out.println(jobService.findall().toString());
        return new Result<List<Job>>(true,"查询所有职位成功","null",jobs);
    }
    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(String jobname){
        Result result = new Result();
        try{
            List<Personnelinfo> list = new ArrayList<>();
            String id = null;
            list = personnelInfoService.findbyjob(jobname);
            int size = list.size();
            for(int i = size-1;i >= 0;i--){
                id = list.get(i).getPersonnelid();
                salaryService.delete(id);
                personneladjustsalaryService.delete(id);
                personnelencourageorpunishService.delete(id);
                personnelremoveService.delete(id);
            }
            personnelInfoService.deletebyjob(jobname);

            jobService.delete(jobname);
            result.setMsg("删除职位成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("删除职位失败！");
        }
        return result;
    }
    @RequestMapping(value = "/save", method = POST ,produces = "application/json;charset=UTF-8")
    public Result save(@RequestBody Job job){
        Result result = new Result();
        try{
            jobService.add(job);
            result.setUserid(job.getJobName());
            result.setMsg("保存职位成功！！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("保存职位失败！");
        }
        return result;
    }
}
