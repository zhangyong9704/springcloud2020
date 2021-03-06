package com.cloud.controller;

import com.cloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback =  "payment_Global_FallbackMethod")  //默认全局熔断的fallback方法
public class PaymentController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 如果@HystrixCommand中未指定fallbackMethod，那么如果超时、异常出现，会默认调用@DefaultProperties
     * 进行处理
     * （该情况是未在PaymentHystrixService中@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentHystrixServiceFallBack.class)）
     * 添加fallback 方法
     * @param id
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        //// int age = 10/0;   //如果方法抛异常，也会执行@DefaultProperties 的 fallback方法
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }

    /**
     * 消费端配置熔断，先要早配置文件中开启熔断，再在主程序上添加@EnableHystrix
     *
     * 这里默认设置超时时间是1.5S
     *
     * 问题：目前每个方法需要配置一个fallback,代码膨胀，可以提供默认的统一兜底方法，并且与业务代码抽离
     *
     *（该情况是未在PaymentHystrixService中@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentHystrixServiceFallBack.class)）
     * 添加fallback 方法
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        // int age = 10/0;   //如果方法抛异常，也会执行fallback方法

        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("*******result:"+result);
        return result;
    }

    //兜底方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }


}