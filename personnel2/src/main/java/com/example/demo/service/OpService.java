package com.example.demo.service;

import com.example.demo.entity.Op;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.entity.User;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/22 21:03
 */
public interface OpService {
    int add(Op op);
    int delete(String opid);
    Op findById(String opid) ;
    int update(Op op);
    List<Op> findAll();
}
