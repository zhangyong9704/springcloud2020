package com.cloud.service.impl;

import com.cloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName MessageProviderImpl.java
 * @Description
 * @CreateDate 2021-03-07  18:18:16
 */

@EnableBinding(Source.class)  ////定义消息的推送管道 ，确定这是消息源
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; // 消息发送管道


    @Override
    public String send() {

        String message = UUID.randomUUID().toString();  //随机序列号
        output.send(MessageBuilder.withPayload(message).build());  //发送消息
        System.out.printf("发送的消息:%s%n", message);

        return message;
    }
}
