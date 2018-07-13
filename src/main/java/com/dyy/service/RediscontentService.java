package com.dyy.service;

import com.dyy.util.PageEntity;
import com.dyy.pojo.Rediscontent;
import com.dyy.pojo.RediscontentExample;

import java.util.List;

public interface RediscontentService {

    List<Rediscontent> selectByExample(RediscontentExample example);
    Rediscontent selectByPrimaryKey(Integer id);
    PageEntity<Rediscontent> selectByPage(Integer pageNum, Integer pageSize);

}