package com.dyy.service;

import com.dyy.pojo.User;
import com.dyy.util.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserById(int userId);
    int createUser(User user) ;
    User findByLogin(User user) ;
    Page<User> findByParams(User u, int pageNo, int limit) ;
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(User record);
    int findAllCount(User u) ;
    List<User> findHotUser() ;

    List<User> findAllByQuery(User user) ;

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<User> list(Map<String, Object> map) ;

    Long getTotal(Map<String, Object> map);

}