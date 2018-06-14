package com.springboot.common.config;

import com.springboot.common.db.DbContextHolder;
import com.springboot.common.db.MasterAndSlaveRoutingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "MasterDataSource")
    @ConfigurationProperties(prefix = "druid.winpos.master")
    public DataSource MasterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "SlaveDataSource")
    @ConfigurationProperties(prefix = "druid.winpos.slave")
    public DataSource SlaveDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource(){
        MasterAndSlaveRoutingDataSource proxy = new MasterAndSlaveRoutingDataSource();
        Map<Object,Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DbContextHolder.DbType.MASTER,MasterDataSource());
        targetDataResources.put(DbContextHolder.DbType.SLAVE,SlaveDataSource());
        proxy.setDefaultTargetDataSource(MasterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

}
