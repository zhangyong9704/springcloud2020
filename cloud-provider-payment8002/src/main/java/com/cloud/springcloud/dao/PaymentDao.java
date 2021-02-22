package com.cloud.springcloud.dao;

import com.cloud.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName PaymentDao.java
 * @Description
 * @CreateDate 2021-02-09  17:18:54
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment select(Long id);
}
