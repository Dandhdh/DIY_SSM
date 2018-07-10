package com.dyy.service.impl;

import com.dyy.dao.UserMapper;
import com.dyy.pojo.User;
import com.dyy.pojo.UserExample;
import com.dyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUid(Integer userid){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        return userMapper.selectByExample(example).get(0);
    }

}
