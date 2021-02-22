package com.cloud.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName EurekaApplication1.java
 * @Description
 * @CreateDate 2021-02-09  20:30:17
 */
@SpringBootApplication
@EnableEurekaServer   //标注当前eureka为服务类
public class EurekaApplication1 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication1.class, args);
    }


}
