package com.springboot.service;

import com.craw.model.Student;
import com.springboot.domain.AreaEntity;

import java.util.List;

public interface AreaService {

    List<AreaEntity> getAllArea();

    int bashInsert(List<Student> list);

}
