package com.dyy.service.dataSource2;

import com.dyy.dao.DatasourceMapper;
import com.dyy.pojo.Datasource;
import com.dyy.pojo.DatasourceExample;
import com.dyy.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DatasourceMapper datasourceMapper;

    @Override
    public List<Datasource> selectByExample(DatasourceExample example) {
        return datasourceMapper.selectByExample(example);
    }
    @Override
    public Datasource selectByPrimaryKey(Integer id) {
        return datasourceMapper.selectByPrimaryKey(id);
    }
}