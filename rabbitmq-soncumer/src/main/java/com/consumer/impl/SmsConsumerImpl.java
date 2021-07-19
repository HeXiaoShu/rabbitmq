package com.consumer.impl;

import com.consumer.SmsConsumer;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Service("smsConsumer")
public class SmsConsumerImpl implements SmsConsumer {
    @Override
    public void orderMessageHandler(String meeage) {
        System.out.println("短信服务："+meeage);
    }
}
