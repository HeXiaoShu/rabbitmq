package com.consumer.impl;

import com.consumer.TtlOrderConsumer;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/5
 * @modify
 */
@Service("ttlOrderConsumerImpl")
public class TtlOrderConsumerImpl implements TtlOrderConsumer {
    @Override
    public void ttlOrderMessageHandler(String meeage) {
        System.out.println("超市订单消费:"+meeage);
    }
}
