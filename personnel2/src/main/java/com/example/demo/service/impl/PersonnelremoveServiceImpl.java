package com.example.demo.service.impl;

import com.example.demo.dao.PersonnelremoveDao;
import com.example.demo.entity.Personnelremove;
import com.example.demo.service.PersonnelremoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:51
 */
@Service
public class PersonnelremoveServiceImpl implements PersonnelremoveService {
    @Autowired
    private PersonnelremoveDao personnelremoveDao;
    @Override
    public int add(Personnelremove personnelremove) {
        return personnelremoveDao.insert(personnelremove);
    }

    @Override
    public int delete(String id) {
        return personnelremoveDao.deleteByPrimaryKey(id);
    }

    @Override
    public int deletebyno(Integer no) {
        return personnelremoveDao.deleteByNo(no);
    }

    @Override
    public int deletebyop(String op) {
        return personnelremoveDao.deletebyop(op);
    }

    @Override
    public List<Personnelremove> findAll() {
        return personnelremoveDao.findAll();
    }

    @Override
    public List<Personnelremove> findAllbyid(String id) {
        return personnelremoveDao.selectByPrimaryKey(id);
    }
}
