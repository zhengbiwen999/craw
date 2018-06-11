package com.zbw.esDemo5.module;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Data
@Document(indexName = "zbw",type = "city")
public class City implements Serializable{

    private static final long serialVersionUID = 7353891786833787224L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceid;

    /**
     * 城市名称
     */
    private String cityname;

    /**
     * 描述
     */
    private String description;

}
