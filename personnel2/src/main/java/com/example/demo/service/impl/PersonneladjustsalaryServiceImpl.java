package com.example.demo.service.impl;

import com.example.demo.dao.PersonneladjustsalaryDao;
import com.example.demo.entity.Personneladjustsalary;
import com.example.demo.service.PersonneladjustsalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/26 10:32
 */
@Service
public class PersonneladjustsalaryServiceImpl implements PersonneladjustsalaryService {
    @Autowired
    private PersonneladjustsalaryDao personneladjustsalaryDao;
    @Override
    public int add(Personneladjustsalary personneladjustsalary) {
        return personneladjustsalaryDao.insert(personneladjustsalary);
    }

    @Override
    public int delete(String id) {
        return personneladjustsalaryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int deletebyno(Integer no) {
        return personneladjustsalaryDao.deleteByNo(no);
    }

    @Override
    public int deletebyop(String op) {
        return personneladjustsalaryDao.deletebyop(op);
    }

    @Override
    public Personneladjustsalary find_new_one(String id) {
        return personneladjustsalaryDao.find_new_one(id);
    }

    @Override
    public List<Personneladjustsalary> findAll() {
        return personneladjustsalaryDao.findAll();
    }

    @Override
    public List<Personneladjustsalary> findAllbyid(String id) {
        return personneladjustsalaryDao.selectByPrimaryKey(id);
    }
}
