package com.consumer.impl;

import com.consumer.TransactionalOrderConsumer;
import com.mapper.DispatchOrderMapper;
import com.model.DispatchOrder;
import com.model.Order;
import com.util.JsonUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/11
 * @modify
 */
@Service("transactionalOrderConsumer")
public class TransactionalOrderConsumerImpl implements TransactionalOrderConsumer {

    @Resource
    DispatchOrderMapper dispatchOrderMapper;

    /**
     * 处理派送订单
     * @param meeage
     */
    @Override
    public void orderHandler(String meeage) {
        Order order = JsonUtil.toObj(meeage,Order.class);
        dispatchOrderMapper.insert(new DispatchOrder().setOrderId(order.getOrderId()).setUserName("何小树").setCreateTime(new Date()));
    }
}
