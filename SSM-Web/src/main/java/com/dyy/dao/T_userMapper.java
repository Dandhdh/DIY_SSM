package com.dyy.dao;

import com.dyy.pojo.T_user;

import java.util.Set;

public interface T_userMapper {
    T_user findUserByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
