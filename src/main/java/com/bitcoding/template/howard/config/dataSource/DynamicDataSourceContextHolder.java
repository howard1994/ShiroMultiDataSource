package com.bitcoding.template.howard.config.dataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {

        @Override
        protected String initialValue() {
            return DataSourceConf.SHIRO_MYSQL;
        }
    };
    public static List<Object> dataSourceKeys = new ArrayList<>();

    public static void setDataSourceKeys(String key){
        contextHolder.set(key);
    }

    public static String getDataSourceKey(){
        return contextHolder.get();
    }

    public static void clearDataSourceKey(){
        contextHolder.remove();
    }
    public static boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }
    public static boolean addDataSourceKeys(Collection<? extends Object> keys){
        return dataSourceKeys.addAll(keys);
    }
}
