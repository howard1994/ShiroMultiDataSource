package com.bitcoding.template.howard.config.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
@Slf4j
public class DynamicDataSourceAspect {
    @Before("@annotation(dataSource)")
    public void switchDataSource(JoinPoint point ,DataSource dataSource){
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())){
            log.info("DataSource [{}] doesn't exist, use default DataSource [{}] " + dataSource.value());
        }
        else{
            DynamicDataSourceContextHolder.setDataSourceKeys(dataSource.value());
            log.info("DataSource [{}] doesn't exist, use default DataSource [{}] " + dataSource.value());
        }
    }
    @After("@annotation(dataSource))")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
    }
}
