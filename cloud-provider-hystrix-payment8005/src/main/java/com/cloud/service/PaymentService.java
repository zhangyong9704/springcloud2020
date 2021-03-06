package com.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //成功
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    //失败

    /**
     * 服务降级
     * 兜底的方法处理，作服务降级fallback
     * @param id
     * @return
     */
    @HystrixCommand( fallbackMethod= "paymentInfo_TimeOutHandler",
            commandProperties = {   //超时异常设置，这里设置为超过3秒，就执行兜底方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"  )
            }
    )
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 5;
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        //int age = 10/0;    //程序执行出现异常也会执行fallback方法
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    /**
     * 服务降级 兜底方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   系统繁忙, 请稍候再试  ,id：  "+id+"\t"+"哭了哇呜";
    }


    /**
     * 服务熔断
     * 参数具体有哪些可以参考：HystrixCommandProperties 属性类
     * 根据官网说明，以下熔断流程：
     *  1、判断在一定时间窗口期内，请求次数是否达到设定值  （默认10秒内超过20个请求次数）
     *  2、如果请求次数在限定时间内达到次数，需要判断在规定请求次数内，失败的概率有没有超过规定的失败率 （默认10秒内超过50%请求失败）
     *  3、如果失败率达到，则会进行服务降级、熔断，当开启的时候，所有请求都不会进行转发
     *  4、一段时间之后（默认是5秒），这个时候断路器是半开状态，允许部分请求通过，如果成功，断路器会关闭，若失败，继续开启。重复1 2 3
     *  5、如果半开状态下成功，则会由半开状态进入开放状态，即恢复当前服务
     *
     *  默认是5S内20调用失败，就会启动熔断机制
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围(时间窗口期)
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }

    /**
     * 服务熔断fallback 处理类
     * @param id
     * @return
     */
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}