package com.example.demo.service.impl;

import com.example.demo.dao.*;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.service.PersonnelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description：
 * DATE:2020/4/23 20:49
 */
@Service
public class PersonnelInfoImpl implements PersonnelInfoService {
    @Autowired
    private PersonnelinfoDao personnelinfoDao;
    @Autowired
    private SalaryDao salaryDao;
    private PersonnelremoveDao personnelremoveDao;
    private PersonnelencourageorpunishDao personnelencourageorpunishDao;
    private PersonneladjustsalaryDao personneladjustsalaryDao;

    @Override
    public List<Personnelinfo> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return personnelinfoDao.findByPage(start,rows);
    }

    @Override
    public Integer findTotals() {
        return personnelinfoDao.findTotals();
    }

    @Override
    public int save(Personnelinfo personnelinfo) {
       /** SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        Date time=new Date();
        String t=sdf.format(time);
        personnelinfo.setWorkstate(t);*/
        return personnelinfoDao.save(personnelinfo);
    }

    @Override
    public int delete(String personnelID) {
        return personnelinfoDao.delete(personnelID);
    }

    @Override
    public int deletebyjob(String jobname) {
        return personnelinfoDao.deleteByJob(jobname);
    }

    @Override
    public int deletebydepartment(String departmentname) {
        return personnelinfoDao.deleteByDepartment(departmentname);
    }

    @Override
    public Personnelinfo findone(String personnelID) {
        return personnelinfoDao.findone(personnelID);
    }

    @Override
    public List<Personnelinfo> findbyjob(String job) {
        return personnelinfoDao.findJobAll(job);
    }

    @Override
    public List<Personnelinfo> findbydepartment(String department) {
        return personnelinfoDao.findDepartmentAll(department);
    }

    @Override
    public List<Personnelinfo> findbysex(String sex) {
        return personnelinfoDao.findBySex(sex);
    }

    @Override
    public List<Personnelinfo> findbysalary(Integer salary) {
        return personnelinfoDao.findBySalary(salary);
    }

    @Override
    public int update(Personnelinfo personnelinfo) {
        return personnelinfoDao.update(personnelinfo);
    }

}
