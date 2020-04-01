package com.baize.framework.dao;

import com.baize.framework.domain.Order;

public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);
}