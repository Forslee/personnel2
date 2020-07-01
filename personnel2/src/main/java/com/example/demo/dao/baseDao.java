package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/23 19:47
 */
@Mapper
public interface baseDao<T,K> {


    int save(T t);
    int update(T t);
    int deleteByJob(K k);
    int deleteByDepartment(K k);
    int delete(K k);

    List<T> findJobAll(K k);
    List<T> findDepartmentAll(K k);
    List<T> findBySex(K k);
    List<T> findBySalary(Integer k);
    //List<T> findByPersonnel(K k);
    //List<T> findAll();
    List<T> findByPage(@Param("start") Integer start,@Param("rows") Integer rows);
    T findone(K k);
    Integer findTotals();
}
