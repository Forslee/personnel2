package com.example.demo.service;


import com.example.demo.entity.Personnelremove;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:47
 */
public interface PersonnelremoveService {
    int add(Personnelremove personnelremove);
    int delete(String id);
    int deletebyno(Integer no);
    int deletebyop(String op);
    List<Personnelremove> findAll();
    List<Personnelremove> findAllbyid(String id);
}
