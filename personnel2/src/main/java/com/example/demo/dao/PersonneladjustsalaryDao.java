package com.example.demo.dao;

import com.example.demo.entity.Job;
import com.example.demo.entity.Personneladjustsalary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PersonneladjustsalaryDao {
    int deleteByPrimaryKey(String id);
    int deleteByNo(Integer no);
    int deletebyop(String op);
    int insert(Personneladjustsalary record);

    int insertSelective(Personneladjustsalary record);

    List<Personneladjustsalary> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Personneladjustsalary record);

    int updateByPrimaryKey(Personneladjustsalary record);
    Personneladjustsalary find_new_one(String id);
    List<Personneladjustsalary> findAll();
}