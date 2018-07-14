package com.dyy.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 确定数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSources();
    }

}
