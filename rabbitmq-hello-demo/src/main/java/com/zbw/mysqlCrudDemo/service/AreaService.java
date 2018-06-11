package com.zbw.mysqlCrudDemo.service;

import com.zbw.mysqlCrudDemo.domain.AreaEntity;
import com.zbw.mysqlCrudDemo.domain.Student;

import java.util.List;

public interface AreaService {

    List<AreaEntity> getAllArea();

    int bashInsert(List<Student> list);

}
