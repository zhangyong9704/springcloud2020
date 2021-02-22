package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PayMentApplication.java
 * @Description
 * @CreateDate 2021-02-09  17:22:38
 */
@SpringBootApplication
@EnableEurekaClient  //声明当前为服务提供者，并注入eureka服务中
@EnableDiscoveryClient  //服务发现
public class PayMentApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(PayMentApplication8001.class, args);
    }
}
