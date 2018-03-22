package com.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCacheController {

//    @Autowired
//    private RedisUtils redisUtils;
//
//    @RequestMapping("/gerCache")
//    String getConfig(){
//        String mealStatusKey = "25854|mealStatus";
//        String paramValue = null;
//        //优先取缓存
//        if (redisUtils.exists(mealStatusKey)) {
//            paramValue = redisUtils.get(mealStatusKey);
//        } else {
//            paramValue = "test";
//            redisUtils.set(mealStatusKey, paramValue,Long.valueOf(8 * 3600));
//        }
//        return paramValue;
//    }


}
