package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName OrderFeignMain80.java
 * @Description
 * @CreateDate 2021-03-06  20:14:21
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class OrderFeignMain8088 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain8088.class,args);
    }
}
