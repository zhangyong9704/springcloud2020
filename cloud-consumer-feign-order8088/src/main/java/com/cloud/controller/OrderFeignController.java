package com.cloud.controller;

import com.cloud.feignclient.PaymentFeignService;
import com.cloud.springcloud.entity.CommonResult;
import com.cloud.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    public CommonResult<Payment> select(@PathVariable("id") Long id){
        return paymentFeignService.select(id);
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/getPort")
    public String getPaymentPort(){
       return paymentFeignService.getServerPort();
    }


    /**
     *
     * @return
     */
    @GetMapping("/discovery")   //服务发现
    public String getDiscoveryClient(){
        return paymentFeignService.getDiscoveryClient();
    }


    /**
     * 演示feign调用接口超时,默认时间是1秒钟,如果需要变更，需要在配置文件中设置
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String getServerPortTimeOut(){
        return paymentFeignService.getServerPortTimeOut();
    }
}