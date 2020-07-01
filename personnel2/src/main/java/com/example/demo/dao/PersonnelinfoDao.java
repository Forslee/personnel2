package com.example.demo.dao;

import com.example.demo.entity.Personnelinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonnelinfoDao extends baseDao<Personnelinfo,String>{

    //   int deleteByPrimaryKey(String personnelid);

    //  int insert(Personnelinfo record);

    //  int insertSelective(Personnelinfo record);

    //  Personnelinfo selectByPrimaryKey(String personnelid)

    //  int updateByPrimaryKeySelective(Personnelinfo record);

    // int updateByPrimaryKey(Personnelinfo record);
}