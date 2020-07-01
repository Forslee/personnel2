package com.example.demo.service.impl;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/25 20:37
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired

    private DepartmentDao departmentDao;
    @Override
    public int delete(String id) {
        return departmentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int add(Department department) {
        return departmentDao.insert(department);
    }

    @Override
    public Department findone(String department_name) {
        return departmentDao.selectByPrimaryKey(department_name);
    }

    @Override
    public List<Department> findall() {
        return departmentDao.findAll();
    }
}
