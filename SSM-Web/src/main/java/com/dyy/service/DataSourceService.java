package com.dyy.service;

import com.dyy.pojo.Datasource;
import com.dyy.pojo.DatasourceExample;

import java.util.List;

public interface DataSourceService {

    List<Datasource> selectByExample(DatasourceExample example);
    Datasource selectByPrimaryKey(Integer id);

}