package com.zbw.esDemo5.repository;

import com.zbw.esDemo5.module.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ElasticsearchRepository<City,Long>{

}
