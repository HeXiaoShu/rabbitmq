package com.model;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/11
 * @modify
 */
@Data
@Accessors(chain = true)
public class DispatchOrder {
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 派单人
     */
    private String userName;
    /**
     * 创建时间
     */
    private Date createTime;


}
