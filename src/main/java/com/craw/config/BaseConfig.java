package com.craw.config;

import lombok.Data;

/**
 * Created by zbww on 2018/3/22.
 */
@Data
public class BaseConfig {

    //最大启动线程数
    private int maxThreads = 5;

    //超时时间
    private int timeout;

    private String keyWords ="";

}
