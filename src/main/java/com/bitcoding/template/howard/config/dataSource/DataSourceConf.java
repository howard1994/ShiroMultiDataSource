package com.bitcoding.template.howard.config.dataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = {"com.bitcoding.template.howard.mapper"})
public class DataSourceConf {
    public static final String SHIRO_MYSQL = "shiro";
    public static final String U8_SQLSERVER = "u8";
    public static final String ERP_POSTGRESQL = "erp";
    @Bean(DataSourceConf.SHIRO_MYSQL)
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.shiro")
    public DataSource shiro(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(DataSourceConf.U8_SQLSERVER)
    @ConfigurationProperties(prefix = "spring.datasource.u8")
    public DataSource u8(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(DataSourceConf.ERP_POSTGRESQL)
    @ConfigurationProperties(prefix = "spring.datasource.erp")
    public DataSource erp(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(3);
        dataSourceMap.put(DataSourceConf.SHIRO_MYSQL, shiro());
        dataSourceMap.put(DataSourceConf.U8_SQLSERVER, u8());
        dataSourceMap.put(DataSourceConf.ERP_POSTGRESQL, erp());

        dynamicDataSource.setDefaultDataSource(shiro());

        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.bitcoding.template.howard.entity");    // 扫描Model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/**/*Mapper.xml"));    // 扫描映射文件
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
