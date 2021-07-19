package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置声明类  死心交换机
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Configuration
public class DeadRabbitMqConfig {

    /**
     * 声明交换机
     * @return FanoutExchange
     */
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange("dead_direct_exchange",true,false);
    }

    /**
     * 声明队列
     * @return Queue
     */
    @Bean
    public Queue deadQueue(){
        return new Queue("dead.direct.queue",true);
    }


    /**
     * 队列-交换机 绑定
     * @return Binding
     */
    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }



}
