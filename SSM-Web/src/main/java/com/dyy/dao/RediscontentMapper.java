package com.dyy.dao;

import com.dyy.pojo.Rediscontent;
import com.dyy.pojo.RediscontentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RediscontentMapper {

    int countByExample(RediscontentExample example);

    int deleteByExample(RediscontentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rediscontent record);

    int insertSelective(Rediscontent record);

    List<Rediscontent> selectByExample(RediscontentExample example);

    Rediscontent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rediscontent record, @Param("example") RediscontentExample example);

    int updateByExample(@Param("record") Rediscontent record, @Param("example") RediscontentExample example);

    int updateByPrimaryKeySelective(Rediscontent record);

    int updateByPrimaryKey(Rediscontent record);
}