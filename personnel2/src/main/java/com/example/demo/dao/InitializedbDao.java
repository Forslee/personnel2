package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Description：
 * DATE:2020/5/15 13:10
 */
@Mapper
public interface InitializedbDao {
     int clear_personnelinfo();
     int clear_personneladjustsalary();
     int clear_personnelencourageorpunish();
     int clear_personnelremove();
     int clear_salary();
     int clear_operate();
     int clear_op();
     int clear_job();
     int clear_department();
     int clear_user();
}
