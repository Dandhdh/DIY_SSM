package com.dyy.dao;

import com.dyy.pojo.Content;

import java.util.List;

public interface ContentMapper {
    List<Content> findContentList();

    int insertSelective(Content content) ;

}