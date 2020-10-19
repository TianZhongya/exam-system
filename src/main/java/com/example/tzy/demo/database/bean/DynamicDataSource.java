package com.example.tzy.demo.database.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Objects;

/**
 * @author: Tianzy
 * @create: 2020-10-19 17:28
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<Object> DATASOURCE_KEY_HOLDER = new InheritableThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return DATASOURCE_KEY_HOLDER.get();
    }

    public static void setDataSourceKey(Object key){
        DATASOURCE_KEY_HOLDER.set(key);
    }

    public static void clearDataSourceKey(){
        DATASOURCE_KEY_HOLDER.remove();
    }
}
