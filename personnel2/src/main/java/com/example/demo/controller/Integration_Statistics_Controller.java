package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.PersonnelInfoService;
import com.example.demo.service.PersonnelencourageorpunishService;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description：员工积分统计：按部门希纳是显示所有员工积分信息（即奖罚分统计：通过该员工所有的奖惩表来统计）
 * DATE:2020/5/14 9:54
 */
@RestController
@CrossOrigin
@Service
@Slf4j
@RequestMapping("/integration_statistics")
public class Integration_Statistics_Controller {

    @Autowired
    private PersonnelencourageorpunishService personnelencourageorpunishService;
    @Autowired
    private PersonnelInfoService personnelInfoService;

    /**
     * 按部门显示所有员工的积分信息，前端显示属性只有id和积分两项，用实体类temp封装
     * @param department
     * @return
     */
    @RequestMapping(value = "/findintegrationbydepartment", method = POST ,produces = "application/json;charset=UTF-8")
    public Result<List<temp>> findintegration(String department){
        List<Personnelinfo> personnelinfos = null;
        List<Personnelencourageorpunish> personnelencourageorpunishes = null;
        List<temp> temps = new ArrayList<>();
        int cardinal = 10;
        try{
           // temps.remove(null);
            personnelinfos = personnelInfoService.findbydepartment(department);
            int size = personnelinfos.size();
            String id = null;
            for(int i = size-1;i >= 0;i--) {
                cardinal = 10;
                id = personnelinfos.get(i).getPersonnelid();
                System.out.println("----"+id+"----");
                personnelencourageorpunishes = personnelencourageorpunishService.findAll(id);
                int size1 = personnelencourageorpunishes.size();
                String type = null; //奖惩类型
                for (int ii = size1 - 1; ii >= 0; ii--) {
                    if(personnelencourageorpunishes.get(ii)!=null)
                    {
                        type = personnelencourageorpunishes.get(ii).getType();
                        if (type.equals("001")) {//此为判断奖惩分类型，001奖励，其他为惩罚
                            cardinal = cardinal + personnelencourageorpunishes.get(ii).getEncorpunishpoint();
                        } else {
                            cardinal = cardinal - personnelencourageorpunishes.get(ii).getEncorpunishpoint();
                        }
                    }
                }
                temp t = new temp();
                t.setUserId(id);
                t.setPoint(cardinal);
                if(t!=null)
                temps.add(t);

            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result<List<temp>>(false,"显示员工积分信息失败","null",null);
        }
        return new Result<List<temp>>(true,"显示员工积分信息成功","null",temps);
    }
}
