package com.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
public class Order {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 创建时间
     */
    private Date createTime;

}
