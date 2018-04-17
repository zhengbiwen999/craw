package com.zbw.es;

import com.zbw.es.module.City;

import java.util.List;

public interface CityService {

    /**
     * 新增城市
     */
    Long saveCity(City city);

    /**
     * 搜索
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
