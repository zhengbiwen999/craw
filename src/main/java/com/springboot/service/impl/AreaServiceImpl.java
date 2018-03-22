package com.springboot.service.impl;

import com.craw.model.Student;
import com.springboot.dao.AreaDao;
import com.springboot.domain.AreaEntity;
import com.springboot.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao areaDao;

    @Override
    public List<AreaEntity> getAllArea() {
        return areaDao.getAllArea();
    }

    @Override
    public int bashInsert(List<Student> list) {
        return areaDao.bashInsert(list);
    }
}
