package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * DATE:2020/4/23 18:16
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;
    @Override

    public User findById(String userid) {

        return userDao.selectByPrimaryKey(userid);
    }

    @Override
    public int add(User user) {
        return  userDao.insert(user);

    }

    @Override
    public int delete(String userid) {
        return userDao.deleteByPrimaryKey(userid);
    }
}
