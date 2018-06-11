package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMQApplication.class,args);
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
        System.out.println("定时测试。。。。。。。");
    }
}
