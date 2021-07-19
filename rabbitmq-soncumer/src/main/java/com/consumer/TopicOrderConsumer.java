package com.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;

/**
 * @Description 注解方式绑定关系
 * @Author Hexiaoshu
 * @Date 2021/7/4
 * @modify
 */
@RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue(value = "sms.topic.queue",durable = "true",autoDelete = "false"),
                exchange = @Exchange(value = "topic_order_exchage",type = ExchangeTypes.TOPIC),
                key = "#.sms.#"
        )
})
public interface TopicOrderConsumer {

    @RabbitHandler
    void orderMessageHandler(String meeage);

}
