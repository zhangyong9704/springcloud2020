package com.cloud.springcloud.service;

import com.cloud.springcloud.entity.Payment;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PaymentService.java
 * @Description
 * @CreateDate 2021-02-09  17:36:48
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment select(Long id);
}
