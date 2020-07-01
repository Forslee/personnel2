package com.example.demo.dao;

import com.example.demo.entity.Op;
import com.example.demo.entity.Operate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OperateDao {
    int deleteByPrimaryKey(String opid);
    int deleteByPrimaryNo(Integer no);
    int insert(Operate record);
    int update(Operate record);
    List<Operate> selectByPrimaryKey(String opid);
    List<Operate> findAll();

}