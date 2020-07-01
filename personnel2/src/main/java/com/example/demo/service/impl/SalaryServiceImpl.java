package com.example.demo.service.impl;

import com.example.demo.dao.SalaryDao;
import com.example.demo.entity.Salary;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 12:19
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryDao salaryDao;
    @Override
    public int save(Salary salary) {
        return salaryDao.insert(salary);
    }

    @Override
    public int delete(String personnelID) {
        return salaryDao.deleteByPrimaryKey(personnelID);
    }

    @Override
    public Salary findone(String personnelID) {
        return salaryDao.selectByPrimaryKey(personnelID);
    }

    @Override
    public int update(Salary salary) {
        return salaryDao.updateByPrimaryKey(salary);
    }

    @Override
    public List<Salary> findSalaryByDepartment(String department) {
        return salaryDao.findSalaryByDepartment(department);
    }

    @Override
    public List<Salary> findSalary_No_processing(String department) {
        return salaryDao.findSalary_No_processing(department);
    }

    @Override
    public List<Salary> findSalary_Having_processing(String department) {
        return salaryDao.findSalary_Having_processing(department);
    }

    @Override
    public List<Salary> findAll() {
        return salaryDao.findAll();
    }
}
