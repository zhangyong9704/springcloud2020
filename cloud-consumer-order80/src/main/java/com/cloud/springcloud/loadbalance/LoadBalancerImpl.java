package com.cloud.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName LoadBalancerImpl.java
 * @Description
 * @CreateDate 2021-03-06  17:13:14
 */
@Component
public class LoadBalancerImpl implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获取访问的点击次数
     * @return
     */
    private int getVisitHitCounts(){

        int current;
        int next;

        do {   //类似于源码的自旋锁+CAS实现
            current = this.atomicInteger.get();   //获得当前下标值
            next = current >= Integer.MAX_VALUE?0:current+1;   //点击次数
        }while (!this.atomicInteger.compareAndSet(current, next));  //第一个参数是期望值，第二个参数是修改
        
        System.out.println("*******第几次访问，次数next: "+next);
        return next;
    }


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {

        if (serviceInstances == null){
            System.out.println("服务器实例个数为空");
            return null;
        }

        int index = getVisitHitCounts() % serviceInstances.size();   //访问次数 % 当前上线的服务机数

        return serviceInstances.get(index);   //返回获得的实例机器
    }
}
