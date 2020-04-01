package com.baize.framework.dao;

import com.baize.framework.domain.CartItem;

public interface CartItemMapper {
    int insert(CartItem record);

    int insertSelective(CartItem record);
}