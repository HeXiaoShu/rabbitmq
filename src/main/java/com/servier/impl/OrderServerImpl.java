package com.servier.impl;

import com.mapper.OrderMapper;
import com.mapper.OrderTmrMapper;
import com.model.MyOrder;
import com.model.MyOrderTmr;
import com.servier.OrderService;
import com.util.JsonUtil;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @Description 两种过去时间同时设置，会以时间最小得 为准
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
@Service("orderServerImpl")
public class OrderServerImpl implements OrderService {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * fanout 发布/订阅 模式模拟
     * 模拟用户下单
     * @param userName
     * @param productId
     * @param number
     */
    @Override
    public void makerOrder(String userName, String productId, int number) {
        String orderId= UUID.randomUUID().toString();
        System.out.println("订单生产成功");
        String exchangeName="fanout_order_exchange";
        String routingKey="";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }

    @Override
    public void makerOrderDirect(String userName, String productId, int number) {
        String orderId= UUID.randomUUID().toString();
        System.out.println("订单生产成功-路由模式：key-order");
        String exchangeName="direct_order_exchange";
        String routingKey="order";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }

    @Override
    public void ttlMakerOrderDirect(String userName, String productId, int number) {
        String orderId= UUID.randomUUID().toString();
        System.out.println("订单生产成功-路由模式-超时设置：key-order");
        String exchangeName="ttl_direct_exchange";
        String routingKey="ttl";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }

    @Override
    public void ttlMessageMakerOrderDirect(String userName, String productId, int number) {
        String orderId= UUID.randomUUID().toString();
        System.out.println("订单生产成功-路由模式-单消息超时设置：key-order");
        String exchangeName="ttl_direct_exchange";
        String routingKey="ttl-message";
        //设置消息过去时间
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration("5000");
            message.getMessageProperties().setContentEncoding("UTF-8");
            return message;
        };
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId,messagePostProcessor);
    }

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderTmrMapper orderTmrMapper;

    @Override
    public void transactionOrder(MyOrder myOrder) {
        String exchangeName="fanout_transaction_order_exchange";
        MyOrderTmr curTmrOrder = new MyOrderTmr().setOrderId(myOrder.getOrderId()).setProductId(myOrder.getProductId()).setOrderName(myOrder.getOrderName()).setCreateTime(new Date()).setStatus(0);
        int insert = orderMapper.insert(myOrder);
        if (insert>0){
            int insert1 = orderTmrMapper.insert(curTmrOrder);
            if (insert1>0){
                //投递消息到交换机
                rabbitTemplate.convertAndSend(exchangeName,"", JsonUtil.toStr(myOrder),new CorrelationData(String.valueOf(myOrder.getOrderId())));
            }
        }
    }

    /**
     * 订单消息投递确认，投递成功修改消息冗余状态
     * 分布式订单业务例子， 订单服务 -> RabbitMq (Exchange) -> 订单配送服务消费订单
     */
    @PostConstruct
    public void regCallBack(){
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("cause:"+cause);
            assert correlationData != null;
            String orderId = correlationData.getId();
            //ack 为true代表收到消息
            if (!ack){
                System.out.println("MQ队列应答--失败，OrderId:"+orderId);
                return;
            }
            try {
                System.out.println("MQ队列应答--成功, 修改订单冗余状态，OrderId:"+orderId);
                //修改冗余消息状态,确认收到消息
                orderTmrMapper.updateByPrimaryKeySelective(new MyOrderTmr().setOrderId(Long.parseLong(orderId)).setStatus(1));
            }catch (Exception ex){
                System.out.println("本地消息出现异常"+ex.getMessage());
            }
        });
    }


}
