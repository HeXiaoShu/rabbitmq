package com.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Description fanout-发布订阅 绑定了交换机得queue都会收到消息 -订单消息处理
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@RabbitListener(queues = {"order.fanout.queue"})
public interface OrderConsumer {

    @RabbitHandler
    void orderMessageHandler(String meeage);

}
