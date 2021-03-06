package com.cloud.feignconfig;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName FeignLogConfig.java
 * @Description
 * @CreateDate 2021-03-06  21:01:51
 */
@Configuration
public class FeignLogConfig {

    /**
     * feign日志级别有四种
     * Level.NONE:默认的，不显示任何日志
     * Level.BASIC:仅记录请求方法、URL、响应状态码及执行时间
     * Level.HEADER:除了basic中定义的信息外，还有请求和相响应的头信息
     * Level.FULL:除了HEADER中定义的信息，还有请求和响应的正文及元数据
     *
     * 还需要在配置文件中指定接口的级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL;
    }
}
