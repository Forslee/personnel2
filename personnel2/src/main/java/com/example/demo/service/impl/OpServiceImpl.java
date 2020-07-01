package com.example.demo.service.impl;

import com.example.demo.dao.OpDao;
import com.example.demo.entity.Op;
import com.example.demo.service.OpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/22 21:04
 */
@Service
@Transactional
public class OpServiceImpl implements OpService {
    @Autowired

    private OpDao opdao;

    @Override
    public int add(Op op) {
        return opdao.insert(op);
        /**
        if(op.getOpname()==null||op.getOpname().equals("")){
            throw new RuntimeException("请输入操作员姓名");
        }else if(op.getOpid()==null||op.getOpid().equals("")){
            throw new RuntimeException("请输入操作员ID");
        }else if(op.getOppassword()==null||op.getOppassword().equals("")){
            throw new RuntimeException("请输入密码");
        }else{
            if (opdao.selectByPrimaryKey(op.getOpname()) == null) {

                opdao.insert(op);
            } else {
                throw new RuntimeException("操作员已存在！");
            }
        } */

    }

    @Override
    public int delete(String opid) {
        return opdao.deleteByPrimaryKey(opid);
    }

    @Override
    public Op findById(String opid) { return opdao.selectByPrimaryKey(opid); }

    @Override
    public int update(Op op) {
        return opdao.updateByPrimaryKey(op);
    }

    @Override
    public List<Op> findAll() {
        return opdao.findAll();
    }
}
