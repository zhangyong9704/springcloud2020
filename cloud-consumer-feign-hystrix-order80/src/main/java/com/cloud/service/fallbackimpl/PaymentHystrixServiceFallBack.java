package com.cloud.service.fallbackimpl;

import com.cloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PaymentHystrixServiceFallBack.java
 * @Description  实现熔断fallback  代码的分类与对应兜底方法与业务代码分离的实现   需要在配置文件中开启熔断服务
 * @CreateDate 2021-03-06  22:39:03
 */

@Component
public class PaymentHystrixServiceFallBack implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }
}
