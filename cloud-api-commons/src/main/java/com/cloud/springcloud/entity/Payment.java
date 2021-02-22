package com.cloud.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName Payment.java
 * @Description
 * @CreateDate 2021-02-09  17:10:22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;

    private String serial;
}
