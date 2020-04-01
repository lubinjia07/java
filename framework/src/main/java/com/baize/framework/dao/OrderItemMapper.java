package com.baize.framework.dao;

import com.baize.framework.domain.OrderItem;

public interface OrderItemMapper {
    int insert(OrderItem record);

    int insertSelective(OrderItem record);
}