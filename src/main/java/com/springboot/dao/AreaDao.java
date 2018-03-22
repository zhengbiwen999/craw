package com.springboot.dao;

import com.craw.model.Student;
import com.springboot.domain.AreaEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface AreaDao extends Mapper, MySqlMapper {

    List<AreaEntity> getAllArea();

    int bashInsert(List<Student> list);

}
