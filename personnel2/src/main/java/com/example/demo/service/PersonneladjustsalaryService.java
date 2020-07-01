package com.example.demo.service;

import com.example.demo.entity.Personneladjustsalary;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:26
 */
public interface PersonneladjustsalaryService {
    int add(Personneladjustsalary personneladjustsalary);
    int delete(String id);
    int deletebyno(Integer no);
    int deletebyop(String op);
    Personneladjustsalary find_new_one(String id);
    List<Personneladjustsalary>  findAll();
    List<Personneladjustsalary>  findAllbyid(String id);
}
