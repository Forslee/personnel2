package com.example.demo.dao;

import com.example.demo.entity.Type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeDao {
    int deleteByPrimaryKey(String typeId);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(String typeId);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}