package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by zbww on 2018/3/25.
 */
@EnableEurekaServer
@SpringBootApplication
@EnableScheduling
public class EurekaService {

    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class,args);
    }

}
