package com.controller;

import com.model.MyOrder;
import com.servier.OrderService;
import com.util.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/11
 * @modify
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/sendOrder")
    public String order(){
        String orderName="康师傅方便面";
        long orderId = SnowflakeIdWorker.id();
        long productId = SnowflakeIdWorker.id();
        MyOrder curMyOrder = new MyOrder().setOrderId(orderId).setProductId(productId).setOrderName(orderName).setCreateTime(new Date());
        orderService.transactionOrder(curMyOrder);
        return "订单生产成功";
    }

}
