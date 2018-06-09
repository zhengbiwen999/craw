package com.zbw;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ValueConfig valueConfig;

    public void send(){
        String context = "hello "+ new Date();
        System.out.println("sender : "+ context);
        System.out.println("配置值是： ===== >"+valueConfig.getEnv());
        this.amqpTemplate.convertAndSend("hello",context);
    }
}
