package com.cloud.springcloud.service.impl;

import com.cloud.springcloud.dao.PaymentDao;
import com.cloud.springcloud.entity.Payment;
import com.cloud.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PaymentServiceImpl.java
 * @Description
 * @CreateDate 2021-02-09  17:38:07
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment select(Long id) {
        return paymentDao.select(id);
    }
}
