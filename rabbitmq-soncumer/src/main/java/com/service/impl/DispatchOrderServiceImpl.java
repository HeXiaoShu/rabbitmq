package com.service.impl;

import com.mapper.DispatchOrderMapper;
import com.model.DispatchOrder;
import com.service.DispatchOrderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/7/11
 * @modify
 */
@Service("dispatchOrderService")
public class DispatchOrderServiceImpl implements DispatchOrderService {

    @Resource
    DispatchOrderMapper dispatchOrderMapper;

    @Override
    public int saveDispatchOrder(String orderId) {
        return dispatchOrderMapper.insert(new DispatchOrder().setOrderId(Long.parseLong(orderId)).setUserName("何小树").setCreateTime(new Date()));
    }
}
