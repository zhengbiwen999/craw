package com.zbw.mysqlCrudDemo.dao;

import com.zbw.mysqlCrudDemo.domain.AreaEntity;
import com.zbw.mysqlCrudDemo.domain.Student;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface AreaDao extends Mapper, MySqlMapper {

    List<AreaEntity> getAllArea();

    int bashInsert(List<Student> list);

}
