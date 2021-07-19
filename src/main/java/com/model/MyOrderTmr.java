package com.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;

/**
 * 订单冗余
 */
@Data
@Accessors(chain = true)
public class MyOrderTmr {

    /**
     * 订单id
     */
    @Id
    private Long orderId;
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String orderName;

    /**
     * 订单状态 0/1  1确认成功投递
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

}
