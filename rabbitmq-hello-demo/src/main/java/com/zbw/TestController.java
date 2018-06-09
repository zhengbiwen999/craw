package com.zbw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {

    @Autowired
    private ValueConfig valueConfig;

    @RequestMapping("/hello")
    public String helloWord(){
        return "hello word"+valueConfig.getEnv()+"value";
    }

}
