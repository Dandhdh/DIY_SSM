package com.dyy.service.impl;

import com.dyy.dao.UserMapper;
import com.dyy.pojo.User;
import com.dyy.service.UserService;
import com.dyy.util.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int createUser(User user) {
        return this.userMapper.insertSelective(user) ;
    }

    @Override
    public User findByLogin(User user) {
        return userMapper.findByLogin(user) ;
    }

    /**
     * 分页查询
     */
    @Override
    public Page<User> findByParams(User u,int pageNo,int limit) {
        Page<User> page = new Page<User>();
        page.setPageNo(pageNo);
        page.setLimit(limit);

        int offset = page.getOffsets();
        RowBounds rowBound = new RowBounds(offset, limit);

        List<User> users = userMapper.findByParams(u,rowBound);
        page.setRows(users);
        int total = userMapper.findAllCount(u) ;
        page.setTotal(total) ;
        if(offset >= page.getTotal()){
            page.setPageNo(page.getTotalPages());
        }
        return page ;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id) ;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int findAllCount(User u) {
        // TODO Auto-generated method stub
        return userMapper.findAllCount(u) ;
    }

    @Override
    public List<User> findHotUser() {
        return userMapper.findHotUser();
    }

    @Override
    public List<User> findAllByQuery(User user) {
        return userMapper.findAllByQuery(user);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return userMapper.getTotal(map);
    }
}