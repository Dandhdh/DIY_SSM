package com.dyy.service.impl;

import com.dyy.dao.RediscontentMapper;
import com.dyy.pojo.Rediscontent;
import com.dyy.pojo.RediscontentExample;
import com.dyy.service.RediscontentService;
import com.dyy.util.PageEntity;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RediscontentServiceImpl implements RediscontentService {

    @Autowired
    private RediscontentMapper rediscontentMapper;


    @Override
    public List<Rediscontent> selectByExample(RediscontentExample example) {
        return rediscontentMapper.selectByExample(example);
    }

    @Override
    public Rediscontent selectByPrimaryKey(Integer id) {
        return rediscontentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageEntity<Rediscontent> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //因为是demo，所以这里默认没有查询条件。
        List<Rediscontent> rediscontents = rediscontentMapper.selectByExample(new RediscontentExample());
        PageEntity<Rediscontent> rediscontentPageEntity = new PageEntity<Rediscontent>();
        rediscontentPageEntity.setList(rediscontents);
        int size = rediscontentMapper.selectByExample(new RediscontentExample()).size();
        rediscontentPageEntity.setCount(size);
        return rediscontentPageEntity;
    }
}
