package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName ConfigCenterMain3344.java
 * @Description  配置中心  从git上拉取配置文件
 * 读取方式：http://localhost:3344/master/application-dev.yml
 * /{label}/{application}-{profile}.yml（最推荐使用这种方式）
 * /{application}-{profile}.yml
 * /{application}-{profile}[/{label}].yml
 * @CreateDate 2021-03-07  14:03:33
 */
@SpringBootApplication
@EnableConfigServer  //启动配置中心
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
