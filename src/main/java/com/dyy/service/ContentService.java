package com.dyy.service;


import com.dyy.pojo.Content;
import java.util.List;

public interface ContentService {

    List<Content> findContentList();

    int insertSelective(Content content) ;
}