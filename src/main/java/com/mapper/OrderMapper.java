package com.mapper;

import com.config.TkMapper;
import com.model.MyOrder;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderMapper extends TkMapper<MyOrder> {

}