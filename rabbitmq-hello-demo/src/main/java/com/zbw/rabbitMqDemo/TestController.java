package com.zbw.rabbitMqDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {

    @Autowired
    private Sender sender;

    @RequestMapping("/mqSender")
    public String helloWord(){
        sender.send();
        return "发送成功！";
    }
}
