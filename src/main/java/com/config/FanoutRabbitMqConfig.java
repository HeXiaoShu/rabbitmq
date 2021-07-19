package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置声明类 Fanout 发布/订阅  给绑定得所有Queue发送消息
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Configuration
public class FanoutRabbitMqConfig {

    /**
     * 声明交换机
     * @return FanoutExchange
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange",true,false);
    }

    /**
     * 声明队列
     * @return Queue
     */
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue");
    }

    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue");
    }

    @Bean
    public Queue orderQueue(){
        return new Queue("order.fanout.queue");
    }

    /**
     * 队列-交换机 绑定
     * @return Binding
     */
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderQueue()).to(fanoutExchange());
    }


}
