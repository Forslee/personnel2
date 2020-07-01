package com.example.demo.service;

import com.example.demo.entity.Job;
import com.example.demo.entity.Operate;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/25 21:36
 */
public interface OperateService {
    int delete(String opid);
    int deleteByNo(Integer No);
    int update(Operate operate);
    int add(Operate operate);
    List<Operate> select(String id);
    List<Operate> findAll();
}
