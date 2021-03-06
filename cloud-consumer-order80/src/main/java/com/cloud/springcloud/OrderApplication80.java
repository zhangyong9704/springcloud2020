package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName OrderApplicarion.java
 * @Description
 * @CreateDate 2021-02-09  19:21:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class OrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class, args);
    }
}
