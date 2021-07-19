package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TTL 过期时间设置
 * @Author Hexiaoshu
 * @Date 2021/7/5
 * @modify
 */
@Configuration
public class TTLRabbitMqConfig {

    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl_direct_exchange",true,false);
    }

    //队列设置过去时间 x-message-ttl
    //exclusive 排他性
    //设置整个Queue里消息得过去时间ms
    //如果队列已经创建，更新参数，需要删除后重新创建； 或者重新创建一个新得，存入新得。
    @Bean
    public Queue ttlDirectOrderQueue(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",10000);
        //绑定死信交换机
        args.put("x-max-length",5);//最大长度5条，超过进入死信队列
        args.put("x-dead-letter-exchange","dead_direct_exchange");
        args.put("x-dead-letter-routing-key","dead");//fanout 不用配置
        return new Queue("ttl_direct.queue",true,false,false,args);
    }

    @Bean
    public Queue ttlDirectMessageOrderQueue(){
        return new Queue("ttl_message_direct.queue",true);
    }

    @Bean
    public Binding ttlDirectOrderBinding(){
        return BindingBuilder.bind(ttlDirectOrderQueue()).to(ttlDirectExchange()).with("ttl");
    }

    @Bean
    public Binding ttlDirectMessageOrderBinding(){
        return BindingBuilder.bind(ttlDirectMessageOrderQueue()).to(ttlDirectExchange()).with("ttl-message");
    }
}
