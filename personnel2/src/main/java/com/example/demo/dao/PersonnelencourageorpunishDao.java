package com.example.demo.dao;


import com.example.demo.entity.Personnelencourageorpunish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PersonnelencourageorpunishDao {
    int deleteByPrimaryKey(String id);
    int deleteByNo(Integer no);
    int deletebyop(String op);
    int insert(Personnelencourageorpunish record);

    int insertSelective(Personnelencourageorpunish record);



    int updateByPrimaryKeySelective(Personnelencourageorpunish record);

    int updateByPrimaryKey(Personnelencourageorpunish record);

    List<Personnelencourageorpunish> selectByPrimaryKey(String id);
    List<Personnelencourageorpunish> findAll1();
}