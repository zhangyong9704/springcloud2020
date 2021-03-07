package com.cloud.controller;

import com.cloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName SendMessageController.java
 * @Description 消息驱动之生产者
 * @CreateDate 2021-03-07  18:27:39
 */

@RestController
public class SendMessageController
{
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage()
    {
        return messageProvider.send();
    }

}
