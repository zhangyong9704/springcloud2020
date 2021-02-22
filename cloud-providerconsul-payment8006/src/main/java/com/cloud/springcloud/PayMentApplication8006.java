package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PayMentApplication8006.java
 * @Description
 * @CreateDate 2021-02-09  17:22:38
 */
@SpringBootApplication
@EnableDiscoveryClient  //声明当前为服务提供者并注册服务
public class PayMentApplication8006 {
    public static void main(String[] args) {
        SpringApplication.run(PayMentApplication8006.class, args);
    }
}
