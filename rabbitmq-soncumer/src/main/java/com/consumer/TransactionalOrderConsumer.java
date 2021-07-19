package com.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/11
 * @modify
 */
@RabbitListener(queues = {"transaction.order.fanout.queue"})
public interface TransactionalOrderConsumer {

    @RabbitHandler
    void orderHandler(String meeage);

}
