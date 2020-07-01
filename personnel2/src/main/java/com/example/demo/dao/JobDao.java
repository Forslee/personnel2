package com.example.demo.dao;

import com.example.demo.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface JobDao {
    int deleteByPrimaryKey(String jobName);

    int insert(Job job);

    int insertSelective(Job record);

    Job selectByPrimaryKey(String jobName);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
    List<Job> findAll();
}