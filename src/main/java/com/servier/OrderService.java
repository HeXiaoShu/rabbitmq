package com.servier;

import com.model.MyOrder;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/3
 * @modify
 */
public interface OrderService {

    void makerOrder(String userName,String productId,int number);

    void makerOrderDirect(String userName,String productId,int number);

    void ttlMakerOrderDirect(String userName,String productId,int number);

    void ttlMessageMakerOrderDirect(String userName,String productId,int number);

    /**
     * 分布式事务 订单处理示例
     * @param myOrder
     */
    void transactionOrder(MyOrder myOrder);

}
