package com.consumer.impl;

import com.consumer.OrderConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Service("orderConsumer")
public class OrderConsumerImpl implements OrderConsumer {

    @Override
    public void orderMessageHandler(String meeage) {
        System.out.println("收到订单："+meeage);
    }
}
