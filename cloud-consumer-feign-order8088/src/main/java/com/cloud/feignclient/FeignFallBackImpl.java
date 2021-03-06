package com.cloud.feignclient;

import com.cloud.springcloud.entity.CommonResult;
import com.cloud.springcloud.entity.Payment;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName FeignFallBackImpl.java
 * @Description   feign 请求失败调用 回调函数
 * @CreateDate 2021-03-06  21:12:03
 */
public class FeignFallBackImpl implements PaymentFeignService {

    private final String RESULT = "系统繁忙，请稍后再试...";

    @Override
    public CommonResult<Payment> select(Long id) {
        return new CommonResult(2202,RESULT);
    }

    @Override
    public String getServerPort() {
        return RESULT;
    }

    @Override
    public String getDiscoveryClient() {
        return RESULT;
    }

    @Override
    public String getServerPortTimeOut() {
        return RESULT;
    }
}
