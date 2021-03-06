package com.cloud.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName LoadBalancer.java
 * @Description  自定义loadbalance接口
 * @CreateDate 2021-03-06  17:10:21
 */
public interface LoadBalancer {

    //收集服务器总共有多少台能够提供服务的机器，并放到list里面
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
