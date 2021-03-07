package com.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName ConfigClientController.java
 * @Description
 * @CreateDate 2021-03-07  15:08:51
 */
@RestController
@RefreshScope   //动态刷新 需要运维人员发送Post请求刷新3366  必须是Post请求  curl -X POST "http://localhost:3366/actuator/refresh"
public class ConfigClientController {


    @Value("${config.info}")
    private String configInfo;

    @Value("${config.env}")
    private String env;

    @Value("${server.port}")
    private String port;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo +":" +env;
    }

    @GetMapping("/port")
    public String getPort(){
        return port;
    }
}
