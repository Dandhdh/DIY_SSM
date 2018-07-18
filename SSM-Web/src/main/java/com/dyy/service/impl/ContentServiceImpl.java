package com.dyy.service.impl;

import com.dyy.dao.ContentMapper;
import com.dyy.pojo.Content;
import com.dyy.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

    @Autowired
    public ContentMapper contentDao ;

    @Override
    public List<Content> findContentList() {
        return contentDao.findContentList();
    }

    @Override
    public int insertSelective(Content content) {
        return contentDao.insertSelective(content);
    }
}
