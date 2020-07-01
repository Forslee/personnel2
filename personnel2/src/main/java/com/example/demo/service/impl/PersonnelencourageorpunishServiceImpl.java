package com.example.demo.service.impl;

import com.example.demo.dao.PersonnelencourageorpunishDao;
import com.example.demo.entity.Personnelencourageorpunish;
import com.example.demo.service.PersonnelencourageorpunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:40
 */
@Service
public class PersonnelencourageorpunishServiceImpl implements PersonnelencourageorpunishService {
    @Autowired
    private PersonnelencourageorpunishDao personnelencourageorpunishDao;
    @Override
    public int add(Personnelencourageorpunish personnelencourageorpunish) {
        return personnelencourageorpunishDao.insert(personnelencourageorpunish);
    }

    @Override
    public int delete(String id) {
        return personnelencourageorpunishDao.deleteByPrimaryKey(id);
    }

    @Override
    public int deletebyno(Integer no) {
        return personnelencourageorpunishDao.deleteByNo(no);
    }

    @Override
    public int deletebyop(String op) {
        return personnelencourageorpunishDao.deletebyop(op);
    }

    @Override
     public List<Personnelencourageorpunish> findAll(String id) {
        return personnelencourageorpunishDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Personnelencourageorpunish> findAll1() {
        return personnelencourageorpunishDao.findAll1();
    }
}
