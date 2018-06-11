package com.zbw.esDemo5.serviceImpl;

import com.zbw.esDemo5.CityService;
import com.zbw.esDemo5.module.City;
import com.zbw.esDemo5.repository.CityRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City result = cityRepository.save(city);
        return result.getId();
    }

    @Override
    public List<City> getCitys() {
        Iterable<City> all = cityRepository.findAll();
        List<City> list = new ArrayList<>();
        all.forEach(ele ->{
            list.add(ele);
        });
        return list;
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100));

        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

        Page<City> searchPageResults = cityRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
}
