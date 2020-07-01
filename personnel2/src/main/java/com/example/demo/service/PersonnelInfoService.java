package com.example.demo.service;

import com.example.demo.entity.Personnelinfo;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/23 20:45
 */
public interface PersonnelInfoService {
    //参数1：当前页   参数2：每页显示记录数
    List<Personnelinfo> findByPage(Integer page,Integer rows);
    //查询总条数
    Integer findTotals();

    //存信息的方法
    int save(Personnelinfo personnelinfo);

    int delete(String personnelID);
    int deletebyjob(String jobname);
    int deletebydepartment(String departmentname);

    Personnelinfo findone(String personnelID);
    List<Personnelinfo> findbyjob(String job);
    List<Personnelinfo> findbydepartment(String department);
    List<Personnelinfo> findbysex(String sex);
    List<Personnelinfo> findbysalary(Integer salary);
    int update(Personnelinfo personnelinfo);

}
