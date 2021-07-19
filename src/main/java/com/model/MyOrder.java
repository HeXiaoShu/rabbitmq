package com.model;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Data
@Accessors(chain = true)
public class MyOrder implements Serializable {

    /**
     * 订单id
     */
    @Id
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
