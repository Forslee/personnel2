package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.entity.Operate;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Result;
import com.example.demo.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：操作员日志的增删改查
 * DATE:2020/5/14 14:03
 */
@RestController
@CrossOrigin
@RequestMapping("/operate")
public class OperateController {
    @Autowired
    private OperateService operateService;

    @RequestMapping(value = "/findall", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Operate>> findall(){
        List<Operate> operates = null;
        try{
            operates = operateService.findAll();
        }catch (Exception e){
            // e.printStackTrace();
            return new Result<List<Operate>>(false,"查询操作员日志失败","null",null);
        }
        return new Result<List<Operate>>(true,"查询操作员日志成功","null",operates);
    }
    @RequestMapping(value = "/findbyopid", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Operate>> findbyopid(String opid){
        List<Operate> operates = null;
        try{
            operates = operateService.select(opid);
        }catch (Exception e){
           // e.printStackTrace();
            return new Result<List<Operate>>(false,"查询操作员日志失败","null",null);
        }
        return new Result<List<Operate>>(true,"查询操作员日志成功","null",operates);
    }

    @RequestMapping(value = "/update", method = POST ,produces = "application/json;charset=UTF-8")
    public Result update(@RequestBody Operate operate){
        Result result = new Result();
        try{
            operateService.update(operate);
            result.setMsg("修改日志成功！！！");
        }catch (Exception e){
            //    e.printStackTrace();
            result.setState(false).setMsg("修改日志失败！");
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(String opid){
        Result result = new Result();
        try{

            operateService.delete(opid);
            result.setMsg("删除该操作员所有日志成功！！！");
        }catch (Exception e){
          //  e.printStackTrace();
            result.setState(false).setMsg("删除日志失败！");
        }
        return result;
    }
    @RequestMapping(value = "/deletebyno", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(Integer no){
        Result result = new Result();
        try{
            operateService.deleteByNo(no);
            result.setMsg("删除日志成功！！！");
        }catch (Exception e){
            //  e.printStackTrace();
            result.setState(false).setMsg("删除日志失败！");
        }
        return result;
    }

    @RequestMapping(value = "/save", method = POST ,produces = "application/json;charset=UTF-8")
    public Result save(@RequestBody Operate operate){
        Result result = new Result();
        try{
            operateService.add(operate);
            result.setMsg("保存日志成功！！！");
        }catch (Exception e){
           e.printStackTrace();
            result.setState(false).setMsg("保存日志失败！");
        }
        return result;
    }
}
