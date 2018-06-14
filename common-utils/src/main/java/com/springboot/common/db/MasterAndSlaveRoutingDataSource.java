package com.springboot.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MasterAndSlaveRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
