package com.example.demo.dao;

import com.example.demo.entity.Sys;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDao {
    int deleteByPrimaryKey(String sysno);

    int insert(Sys record);

    int insertSelective(Sys record);

    Sys selectByPrimaryKey(String sysno);

    int updateByPrimaryKeySelective(Sys record);

    int updateByPrimaryKey(Sys record);
}