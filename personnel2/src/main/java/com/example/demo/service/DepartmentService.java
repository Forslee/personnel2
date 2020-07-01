package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/25 19:58
 */
public interface DepartmentService {
    int delete(String id);
    int add(Department department);
    Department findone(String department_name);
    List<Department> findall();
}
