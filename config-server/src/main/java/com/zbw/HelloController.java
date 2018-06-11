package com.zbw;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zbww on 2018/3/27.
 */
@RestController
@RefreshScope
public class HelloController {

    @RequestMapping("/hellp")
    public String helloWorld(){
        return "hello world";
    }
}
