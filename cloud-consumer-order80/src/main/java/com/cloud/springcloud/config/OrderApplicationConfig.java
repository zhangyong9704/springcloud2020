package com.cloud.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName OrderApplicationConfig.java
 * @Description
 * @CreateDate 2021-02-09  19:01:06
 */
@Configuration
public class OrderApplicationConfig {

    @Bean
    @LoadBalanced   //启用负载均衡功能   (这个关闭，会使用自定义负载均衡算法,只针对http://localhost/consume/load 这个请求)
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
