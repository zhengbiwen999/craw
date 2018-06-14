package com.zbw.mysqlCrudDemo.service.impl;

import com.springboot.common.annotation.Master;
import com.springboot.common.annotation.Slave;
import com.zbw.mysqlCrudDemo.dao.AreaDao;
import com.zbw.mysqlCrudDemo.domain.AreaEntity;
import com.zbw.mysqlCrudDemo.domain.Student;
import com.zbw.mysqlCrudDemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    @Slave
    public List<AreaEntity> getAllArea() {
        return areaDao.getAllArea();
    }


    @Override
    @Master
    public int insertEntity(AreaEntity entity) {
        return areaDao.insertEntity(entity);
    }


    @Override
    @Master
    public int bashInsert(List<Student> list) {
        return areaDao.bashInsert(list);
    }
}
