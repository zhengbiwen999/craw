package com.zbw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zbww on 2018/3/27.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String helloWorld(){
        return "hello world123";
    }
}