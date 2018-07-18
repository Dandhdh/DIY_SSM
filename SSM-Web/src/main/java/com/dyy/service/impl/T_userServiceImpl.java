package com.dyy.service.impl;

import com.dyy.dao.T_userMapper;
import com.dyy.pojo.T_user;
import com.dyy.service.T_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("t_userService")
public class T_userServiceImpl implements T_userService {

    @Autowired
    private T_userMapper t_userMapper;

    @Override
    public T_user findUserByUsername(String username) {
        T_user t_user = t_userMapper.findUserByUsername(username);
        return t_user;
    }

    @Override
    public Set<String> findRoles(String username) {
        return t_userMapper.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return t_userMapper.findPermissions(username);
    }

}
