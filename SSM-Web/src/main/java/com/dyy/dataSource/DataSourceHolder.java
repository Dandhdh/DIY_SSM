package com.dyy.dataSource;

/**
 * 动态数据源
 */
public class DataSourceHolder {

    // 这里使用了ThreadLocal来保存了数据源
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSources(String dataSource) {
        dataSources.set(dataSource);
    }

    public static String getDataSources() {
        return dataSources.get();
    }
}