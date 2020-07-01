package com.example.demo.dao;

import com.example.demo.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DepartmentDao {
    int deleteByPrimaryKey(String departmentName);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String departmentName);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    List<Department> findAll();
}