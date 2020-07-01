package com.example.demo.service.impl;

import com.example.demo.dao.InitializedbDao;
import com.example.demo.service.InitializedbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * DATE:2020/5/15 13:55
 */
@Service
public class InitializedbServiceImpl implements InitializedbService {
    @Autowired
    private InitializedbDao initializedbDao;
    @Override
    public int clear_personnelinfo() {
        return initializedbDao.clear_personnelinfo();
    }
    @Override
    public int clear_personneladjustsalary() {
        return initializedbDao.clear_personneladjustsalary();
    }
    @Override
    public int clear_personnelencourageorpunish() {
        return initializedbDao.clear_personnelencourageorpunish();
    }
    @Override
    public int clear_personnelremove() { return initializedbDao.clear_personnelremove(); }
    @Override
    public int clear_salary() {
        return initializedbDao.clear_salary();
    }
    @Override
    public int clear_operate() {
        return initializedbDao.clear_operate();
    }
    @Override
    public int clear_op() {
        return initializedbDao.clear_op();
    }
    @Override
    public int clear_job() {
        return initializedbDao.clear_job();
    }
    @Override
    public int clear_department() {
        return initializedbDao.clear_department();
    }
    @Override
    public int clear_user() {
        return initializedbDao.clear_user();
    }
}
