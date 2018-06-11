package com.zbw.esDemo5;

import com.zbw.esDemo5.module.City;

import java.util.List;

public interface CityService {

    /**
     * 新增城市
     */
    Long saveCity(City city);

    List<City> getCitys();

    /**
     * 搜索
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
