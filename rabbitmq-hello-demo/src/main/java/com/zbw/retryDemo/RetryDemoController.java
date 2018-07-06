package com.zbw.retryDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryDemoController {

    @Autowired
    private RetryService RetryService;

    @RequestMapping("/retryDemo")
    private String retryDemo(){
        RetryService.testRetry();
        return "success";
    }

}
