package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Personnelencourageorpunish;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Result;
import com.example.demo.entity.Sys;
import com.example.demo.service.PersonnelInfoService;
import com.example.demo.service.PersonnelencourageorpunishService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：员工奖惩
 * DATE:2020/4/27 9:31
 */
@RestController
@CrossOrigin
@RequestMapping("/personnelencourageorpunish")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//先试试不知道行不行

public class PersonnelencourageorpunishController {
    @Autowired
    private PersonnelencourageorpunishService personnelencourageorpunishService;
    @Autowired
    private PersonnelInfoService personnelInfoService;
    //private Date time;

    /**
     * 按id查找惩罚表
     * @param id
     * @return
     */
    @RequestMapping(value = "/findbyid", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personnelencourageorpunish>> findbyid(String id){
        List<Personnelencourageorpunish> personnelencourageorpunishes = null;
        try{
            personnelencourageorpunishes = personnelencourageorpunishService.findAll(id);
        }catch (Exception e){
          //  e.printStackTrace();
            return new Result<List<Personnelencourageorpunish>>(false,"查询所有奖惩表失败","null",null);
        }
        return new Result<List<Personnelencourageorpunish>>(true,"查询所有奖惩表成功","null",personnelencourageorpunishes);
    }

    @RequestMapping(value = "/findAll", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personnelencourageorpunish>> findAll(){
        List<Personnelencourageorpunish> personnelencourageorpunishes = null;
        try{
            personnelencourageorpunishes = personnelencourageorpunishService.findAll1();
        }catch (Exception e){
          //  e.printStackTrace();
            return new Result<List<Personnelencourageorpunish>>(false,"查询所有奖惩表失败","null",null);
        }
        return new Result<List<Personnelencourageorpunish>>(true,"查询所有奖惩表成功","null",personnelencourageorpunishes);
    }

    /**
     * 通过奖惩分实行奖罚
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = POST ,produces = "application/json;charset=UTF-8")
    public Result add(@RequestBody Personnelencourageorpunish personnelencourageorpunish){

        Result result = new Result();
        //System.out.println(personnelencourageorpunish.getPersonnelid());
        Personnelinfo personnelinfo = personnelInfoService.findone(personnelencourageorpunish.getPersonnelid());
        if(personnelinfo==null){
            result.setMsg("操作失败，没有该员工!");
        }else{
            try{
                personnelencourageorpunishService.add(personnelencourageorpunish);
                result.setMsg("完成操作！！！");
            }catch (Exception e){
           //     e.printStackTrace();
                result.setState(false).setMsg("操作失败！");
            }
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(  String personnelid){
        Result result = new Result();
        try{
           // result.setUserid(personnelid);
            personnelencourageorpunishService.delete(personnelid);
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
            personnelencourageorpunishService.deletebyno(no);
            result.setMsg("奖惩表删除信息成功！！！");
        }catch (Exception e){
            // e.printStackTrace();
            result.setState(false).setMsg("删除信息失败！");
        }
        return result;
    }
}
