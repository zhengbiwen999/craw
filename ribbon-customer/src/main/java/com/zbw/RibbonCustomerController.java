package com.zbw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonCustomerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/ribbon-customer")
    public String helloCustomer(){
        return helloService.helloService();
    }
}


