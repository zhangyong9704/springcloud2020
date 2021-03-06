package com.cloud.feignclient;

import com.cloud.springcloud.entity.CommonResult;
import com.cloud.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PaymentFeignService.java
 * @Description  使用fallback   需要在配置文件中开启熔断服务
 * @CreateDate 2021-03-06  20:16:18
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = FeignFallBackImpl.class)
public interface PaymentFeignService {

    /**
     * 注意请求路径要和提供共请求一致
     * @param id
     * @return
     */
    @GetMapping("/payment/select/{id}")
    public CommonResult<Payment> select(@PathVariable("id") Long id);

    /**
     *
     * @return
     */
    @GetMapping(value = "/payment/port")
    public String getServerPort();

    /**
     *
     * @return
     */
    @GetMapping("/payment/discovery")   //服务发现
    public String getDiscoveryClient();

    /**
     * 演示feign调用接口超时
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String getServerPortTimeOut();
}
