package com.dyy.service;


import com.alibaba.fastjson.JSON;
import com.dyy.pojo.Datasource;
import com.dyy.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/*.xml","classpath:spring/*.xml"})
public class DataSourceServiceTest {

    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {
        User user = userService.getUserById(11);
        Datasource datasource = dataSourceService.selectByPrimaryKey(7);
        System.out.println(user.getUsername());
        System.out.println(datasource.toString());
    }

}