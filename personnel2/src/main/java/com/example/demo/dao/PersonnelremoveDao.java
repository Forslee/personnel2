package com.example.demo.dao;

import com.example.demo.entity.Personnelencourageorpunish;
import com.example.demo.entity.Personnelremove;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PersonnelremoveDao {
    int deleteByPrimaryKey(String id);
    int deleteByNo(Integer no);
    int deletebyop(String op);
    int insert(Personnelremove record);

    int insertSelective(Personnelremove record);

    List<Personnelremove> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Personnelremove record);

    int updateByPrimaryKey(Personnelremove record);
    List<Personnelremove> findAll();
}