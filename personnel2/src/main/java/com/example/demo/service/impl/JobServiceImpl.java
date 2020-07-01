package com.example.demo.service.impl;

import com.example.demo.dao.JobDao;
import com.example.demo.entity.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/25 20:56
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;
    @Override
    public int delete(String id) {

        return jobDao.deleteByPrimaryKey(id);
    }

    @Override
    public int add(Job job) {
        return jobDao.insert(job);
    }

    @Override
    public Job findone(String job_name) {
        return jobDao.selectByPrimaryKey(job_name);
    }

    @Override
    public List<Job> findall() {
        return jobDao.findAll();
    }
}
