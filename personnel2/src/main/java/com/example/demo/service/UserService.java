package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Description：
 * DATE:2020/4/23 18:09
 */
public interface UserService {

   // List<User> queryAllUser(Integer page, Integer pageSize);

    //根据id查找用户
    User findById(String userid) ;

    //保存用户登录信息
    int add(User user);

    //根据姓名删除
     int delete(String userid);


}
