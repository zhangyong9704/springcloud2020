package com.cloud.springcloud.controller;

import com.cloud.springcloud.entity.CommonResult;
import com.cloud.springcloud.entity.Payment;
import com.cloud.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PaymentController.java
 * @Description
 * @CreateDate 2021-02-09  17:41:08
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/insert")
    public CommonResult insert(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        return i>0?new CommonResult(200,"添加成功,Port:"+serverPort,i):new CommonResult(202,"添加失败,Port:"+serverPort,null);

    }

    @GetMapping("/select/{id}")
    public CommonResult select(@PathVariable("id") Long id){
        Payment payment = paymentService.select(id);
        return !StringUtils.isEmpty(payment)?new CommonResult(200,"查询成功,Port:"+serverPort,payment):new CommonResult(202,"查询失败,Port:"+serverPort,null);
    }



    @GetMapping("/discovery")   //服务发现
    public Object getDiscoveryClient(){

        List<String> services = discoveryClient.getServices();  //获得已注册的服务
        System.out.println(services);  //已注册的应用信息  [cloud-payment-service, cloud-order-service]

        //获取某个已注册服务下的具体实例名称
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
            //192.168.1.6	8002	http://192.168.1.6:8002
        }
        return services;
    }
}
