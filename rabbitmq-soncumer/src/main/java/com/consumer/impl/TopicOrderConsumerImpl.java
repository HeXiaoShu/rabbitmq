package com.consumer.impl;

import com.consumer.TopicOrderConsumer;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/4
 * @modify
 */
@Service("topicOrderConsumer")
public class TopicOrderConsumerImpl implements TopicOrderConsumer {
    @Override
    public void orderMessageHandler(String meeage) {
        System.out.println("主题模式订单："+meeage);
    }
}
