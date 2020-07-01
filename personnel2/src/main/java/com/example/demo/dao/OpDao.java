package com.example.demo.dao;

import com.example.demo.entity.Op;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpDao {
    int deleteByPrimaryKey(String opid);

    int insert(Op record);

    int insertSelective(Op record);

    Op selectByPrimaryKey(String opid);

    int updateByPrimaryKeySelective(Op record);

    int updateByPrimaryKey(Op record);
    List<Op> findAll();
}