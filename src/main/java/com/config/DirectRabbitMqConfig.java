package com.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置声明类  Direct 路由模式，添加绑定 Routingkey
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Configuration
public class DirectRabbitMqConfig {

    /**
     * 声明交换机
     * @return FanoutExchange
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }

    /**
     * 声明队列
     * @return Queue
     */
    @Bean
    public Queue directSmsQueue(){
        return new Queue("sms.direct.queue");
    }

    @Bean
    public Queue directEmailQueue(){
        return new Queue("email.direct.queue");
    }

    @Bean
    public Queue directOrderQueue(){
        return new Queue("order.direct.queue");
    }

    /**
     * 队列-交换机 绑定
     * @return Binding
     */
    @Bean
    public Binding directSmsBinding(){
        return BindingBuilder.bind(directSmsQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding directEmailBinding(){
        return BindingBuilder.bind(directEmailQueue()).to(directExchange()).with("email");
    }

    @Bean
    public Binding directOrderBinding(){
        return BindingBuilder.bind(directOrderQueue()).to(directExchange()).with("order");
    }


}
