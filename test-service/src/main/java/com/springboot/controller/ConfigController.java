package com.springboot.controller;

import com.springboot.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    WebConfig webConfig;

    /**
     * 根据dev 、test 环境，在 application-{profile}.yml 中获取配置信息
     */
    @RequestMapping("/getConfig")
    String getConfig(){
        String value = webConfig.getProvince()+": "+webConfig.getCity()+": "+webConfig.getDesc();
        return value;
    }


}
