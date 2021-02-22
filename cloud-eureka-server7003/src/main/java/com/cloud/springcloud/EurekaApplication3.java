package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName EurekaApplication2.java
 * @Description
 * @CreateDate 2021-02-09  21:12:18
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication3 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication3.class, args);
    }

}
