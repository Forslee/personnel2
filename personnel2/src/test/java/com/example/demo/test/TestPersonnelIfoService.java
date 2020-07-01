package com.example.demo.test;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Personnelinfo;
import com.example.demo.service.PersonnelInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Description：
 * DATE:2020/4/23 20:59
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestPersonnelIfoService {
    @Autowired
    private PersonnelInfoService personnelInfoService;

    @Test
    public  void testFindByPage(){
        List<Personnelinfo> list = personnelInfoService.findByPage(1,5);
        list.forEach(pr->{
            System.out.println("我是你爸");
            System.out.println(pr);
        });
    }
}
