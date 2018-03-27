package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean

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

    /**
     * 配上健康检查
     * http://blog.springcloud.cn/sc/sc-lx/
     */

    /**
     * 定时任务，每隔一分钟打印一次 xxx
     *  corn 表达式  https://www.jianshu.com/p/f03b1497122a
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void someTimeTask() {
        System.out.println("xxx");
    }
}
