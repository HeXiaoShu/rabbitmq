package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description MQ分布书事务，消息可靠生产
 * @Author Hexiaoshu
 * @Date 2021/7/10
 * @modify
 */
@Configuration
public class TransactionalRabbitMqConfig {

    /**
     * 声明交换机
     * @return FanoutExchange
     */
    @Bean
    public FanoutExchange transactionFanoutExchange(){
        return new FanoutExchange("fanout_transaction_order_exchange",true,false);
    }

    @Bean
    public Queue transactionOrderQueue(){
        return new Queue("transaction.order.fanout.queue");
    }


    @Bean
    public Binding transactionOrderBinding(){
        return BindingBuilder.bind(transactionOrderQueue()).to(transactionFanoutExchange());
    }


}
