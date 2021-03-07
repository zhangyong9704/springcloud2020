package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author zy
 * @version 1.0.0
 * @ClassName GateWayMain9527.java
 * @Description
 * @CreateDate 2022-02-09  17:41:08
 */

@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527 {
    public static void main(String[] args) {
            SpringApplication.run( GateWayMain9527.class,args);
        }
}
 