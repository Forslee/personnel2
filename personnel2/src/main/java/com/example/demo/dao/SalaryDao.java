package com.example.demo.dao;

import com.example.demo.entity.Personnelremove;
import com.example.demo.entity.Salary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalaryDao {
    int deleteByPrimaryKey(String personnelid);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(String personnelid);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);
    List<Salary> findSalaryByDepartment(String department);
    List<Salary> findSalary_No_processing(String department);
    List<Salary> findSalary_Having_processing(String department);
    List<Salary> findAll();
}