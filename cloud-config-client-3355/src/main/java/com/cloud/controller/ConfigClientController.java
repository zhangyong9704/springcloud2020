package com.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName ConfigClientController.java
 * @Description
 * @CreateDate 2021-03-07  15:08:51
 */
public class ConfigClientController {


    @Value("${spring.application.name}")
    private String configInfo;

    @Value("${server.port}")
    private String port;

    @GetMapping("/config")
    public String getConfigInfo(){
        return configInfo +":" +port;
    }
}
