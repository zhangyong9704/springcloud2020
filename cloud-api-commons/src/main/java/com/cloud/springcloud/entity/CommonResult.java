package com.cloud.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName CommonResult.java
 * @Description
 * @CreateDate 2021-02-09  17:13:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }

}
