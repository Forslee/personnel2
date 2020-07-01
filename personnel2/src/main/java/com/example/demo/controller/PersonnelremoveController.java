package com.example.demo.controller;
import com.example.demo.entity.Personneladjustsalary;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Personnelremove;
import com.example.demo.entity.Result;
import com.example.demo.service.PersonnelInfoService;
import com.example.demo.service.PersonnelremoveService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：员工调动
 * DATE:2020/4/29 12:35
 */
@RestController
@CrossOrigin
@RequestMapping("personnelremove")
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//先试试不知道行不行
public class PersonnelremoveController {
    @Autowired
    private PersonnelremoveService personnelremoveService;
   // @Autowired
    //private Date time;
    @Autowired
    private PersonnelInfoService personnelInfoService;
    //@Override


    @RequestMapping(value = "/findAll", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personnelremove>> findAll(){
        List<Personnelremove> personnelremoves = null;
        try{
            personnelremoves = personnelremoveService.findAll();
        }catch (Exception e){
         //   e.printStackTrace();
            return new Result<List<Personnelremove>>(false,"查询所有调动表失败","null",null);
        }
        return new Result<List<Personnelremove>>(true,"查询所有调动表成功","null",personnelremoves);
    }


    @RequestMapping(value = "/findbyid", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<Personnelremove>> findbyid(String id){
        List<Personnelremove> personnelremoves = null;
        try{
           personnelremoves = personnelremoveService.findAllbyid(id);
        }catch (Exception e){
            //  e.printStackTrace();
            return new Result<List<Personnelremove>>(false,"查询所有调动表失败","null",null);
        }
        return new Result<List<Personnelremove>>(true,"查询所有调动表成功","null",personnelremoves);
    }

    /**
     * 员工调动：增加员工调动表，修改员工信息
     * @param personnelremove
     * @return
     */
    @RequestMapping(value = "/update", method = POST ,produces = "application/json;charset=UTF-8")
    public Result update(@RequestBody Personnelremove personnelremove) {
        Result result = new Result();
        String id = personnelremove.getPersonnelid();
        Personnelinfo personnelinfo = personnelInfoService.findone(id);
        if (personnelinfo == null) {
            result.setMsg("操作失败，没有该用户！！！");
        } else {
            try{
                personnelremoveService.add(personnelremove);
                personnelinfo.setJob(personnelremove.getAfterremovejob());
                personnelinfo.setDepartment(personnelremove.getAfterremovedepartment());
                personnelInfoService.update(personnelinfo);
                result.setMsg("完成操作！！！");
            }catch (Exception e){
             //   e.printStackTrace();
                result.setState(false).setMsg("操作失败！");
            }
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(String opid){
        Result result = new Result();
        try{

            personnelremoveService.delete(opid);
            result.setMsg("删除调动表成功！！！");
        }catch (Exception e){
            //  e.printStackTrace();
            result.setState(false).setMsg("删除调动表失败！");
        }
        return result;
    }
    @RequestMapping(value = "/deletebyno", method = POST ,produces = "application/json;charset=UTF-8")
    public Result delete(Integer no){
        Result result = new Result();
        try{
            personnelremoveService.deletebyno(no);
            result.setMsg("删除调动表成功！！！");
        }catch (Exception e){
            //  e.printStackTrace();
            result.setState(false).setMsg("删除调动表失败！");
        }
        return result;
    }
}
