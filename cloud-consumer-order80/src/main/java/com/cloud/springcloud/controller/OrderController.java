package com.cloud.springcloud.controller;

import com.cloud.springcloud.entity.CommonResult;
import com.cloud.springcloud.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName OrderController.java
 * @Description
 * @CreateDate 2021-02-09  19:03:28
 */
@RestController
@RequestMapping("/consume")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

//    private static final String REQUEST_URL = "http://localhost:8001/payment";
    private static final String REQUEST_URL = "http://CLOUD-PAYMENT-SERVICE";   //负载均衡访问，配置集群对外接口


    /**
     * getForObject()可以理解为返回的对象形式为json格式的
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    public CommonResult<Payment> selectPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(REQUEST_URL+"/payment/select/"+id,CommonResult.class);
    }

    /**
     * 返回对象为ResponseEntity对象，包含了响应的一些重要信息，比如响应头、响应状态码、响应体等
     * @param id
     * @return
     */
    @GetMapping("/select/entity/{id}")
    public CommonResult selectPaymentByEntity(@PathVariable("id") Long id){

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(REQUEST_URL + "/payment/select/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()){   //获得200开头的状态码
            return entity.getBody();
        }
        return new CommonResult(444,"操作失败");
    }

    @GetMapping("/insert")
    public CommonResult<Payment> insertPayment(Payment payment){

        return restTemplate.postForObject(REQUEST_URL+"/payment/insert", payment, CommonResult.class);
//        return restTemplate.postForEntity(REQUEST_URL+"/payment/insert", payment, CommonResult.class).getBody();
    }






}
