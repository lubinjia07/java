package com.baize.framework.dao;

import com.baize.framework.domain.Shipping;

public interface ShippingMapper {
    int insert(Shipping record);

    int insertSelective(Shipping record);
}