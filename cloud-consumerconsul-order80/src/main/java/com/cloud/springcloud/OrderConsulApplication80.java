package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName OrderConsulApplication80.java
 * @Description
 * @CreateDate 2021-02-09  19:21:25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulApplication80.class, args);
    }
}
