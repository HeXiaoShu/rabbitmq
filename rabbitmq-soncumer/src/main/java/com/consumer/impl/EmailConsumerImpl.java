package com.consumer.impl;

import com.consumer.EmailConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description 订单消息处理
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Service("emailConsumer")
public class EmailConsumerImpl implements EmailConsumer {

    @Override
    public void orderMessageHandler(String meeage) {
        System.out.println("邮件发送："+meeage);
    }
}
