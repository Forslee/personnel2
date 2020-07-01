package com.example.demo.service.impl;

import com.example.demo.dao.OperateDao;
import com.example.demo.entity.Operate;
import com.example.demo.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/25 21:39
 */
@Service
public class OperateServiceImpl implements OperateService {
    @Autowired
    private OperateDao operateDao;
    @Override
    public int delete(String opid) {
        return operateDao.deleteByPrimaryKey(opid);
    }

    @Override
    public int deleteByNo(Integer No) {
        return operateDao.deleteByPrimaryNo(No);
    }

    @Override
    public int update(Operate operate) {
        return operateDao.update(operate);
    }

    @Override
    public int add(Operate operate) {
        return operateDao.insert(operate);
    }

    @Override
    public List<Operate> select(String id) {
        return operateDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Operate> findAll() {
        return operateDao.findAll();
    }
}
