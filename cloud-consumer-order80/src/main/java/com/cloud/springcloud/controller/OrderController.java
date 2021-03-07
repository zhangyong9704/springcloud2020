package com.cloud.springcloud.controller;

import com.cloud.springcloud.entity.CommonResult;
import com.cloud.springcloud.entity.Payment;
import com.cloud.springcloud.loadbalance.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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

    @Resource
    LoadBalancer loadBalancer;   //自定义的的负载均衡算法

    @Resource
    private DiscoveryClient discoveryClient;   //发现服务

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


    /**
     * 测试自定义的负载均衡算法
     * @return String
     */
    @GetMapping("/load")
    public String testLoadBalance(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");  //获取存在的实例

        if (instances ==null || instances.size()<=0){
            System.out.println("服务实例未存在，请求失败");
            return "服务实例未存在，请求失败";
        }
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();   //获得访问的资源地址

        return restTemplate.getForObject(uri + "/payment/discovery", String.class);
    }




    /**
     * 调用8001 测试链路调用 zipkin+sleuth  http://localhost:9411/zipkin/ 图像化地址
     * @return
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return restTemplate.getForObject(REQUEST_URL+"/payment/zipkin", String.class);
    }


}
