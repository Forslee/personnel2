package com.example.demo.test;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Op;
import com.example.demo.service.OpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description：
 * DATE:2020/4/22 21:11
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestOpService {
    @Autowired
    private OpService opservice;

    @Test
    public void testRegister() {
        Op op = new Op();
        op.setOpname("111");
        op.setOpid("12345");
        op.setOppassword("123321");
        op.setOpphone("15831220192");
        op.setRemark("略");
        op.setPermission("1");
        opservice.add(op);
    }
}
