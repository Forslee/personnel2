package com.example.demo.service;

import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.Salary;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:57
 */
public interface SalaryService {
    int save(Salary salary);

    int delete(String personnelID);

    Salary findone(String personnelID);

    int update(Salary salary);
    List<Salary> findSalaryByDepartment(String department);
    List<Salary> findSalary_No_processing(String department);
    List<Salary> findSalary_Having_processing(String department);
    List<Salary> findAll();
}
