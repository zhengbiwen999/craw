package com.springboot.controller;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final Logger logger = Logger.getLogger(AreaController.class);

    @RequestMapping("/hello")
    public String helloWord(){
        return "hello word";
    }

}
