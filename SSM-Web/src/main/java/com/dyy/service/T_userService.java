package com.dyy.service;

import com.dyy.pojo.T_user;

import java.util.Set;

/**
 * 后台登陆service
 */
public interface T_userService {

    /**
     * shiro的登陆验证，通过用户名查询用户信息
     */
    public T_user findUserByUsername(String username) ;

    /**
     * 根据账号查找角色名称
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) ;

    /**
     * 根据账号查找权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) ;

}
