package com.cloud.service;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName IMessageProvider.java
 * @Description  消息发送的接口
 * @CreateDate 2021-03-07  18:17:15
 */
public interface IMessageProvider {

    /**
     * 发送消息到mq
     * @return
     */
    public String send();


}
