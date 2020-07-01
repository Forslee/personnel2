package com.example.demo.service;

import com.example.demo.entity.Personnelencourageorpunish;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:37
 */
public interface PersonnelencourageorpunishService {
    int add(Personnelencourageorpunish personnelencourageorpunish);
    int delete(String id);
    int deletebyno(Integer no);
    int deletebyop(String op);
   // void deleteBydepartment(String department);
   // int update(Personnelencourageorpunish personnelencourageorpunish);
    //Personnelencourageorpunish find_new_one(String id);
    List<Personnelencourageorpunish> findAll(String id);

    List<Personnelencourageorpunish> findAll1();
}
