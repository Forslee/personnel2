package com.example.demo.service;

import com.example.demo.entity.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/25 20:53
 */
public interface JobService {
    int delete(String id);
    int add(Job job);
    Job findone(String job_name);
    List<Job> findall();
}
