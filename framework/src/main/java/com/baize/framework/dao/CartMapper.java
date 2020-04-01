package com.baize.framework.dao;

import com.baize.framework.domain.Cart;

public interface CartMapper {
    int insert(Cart record);

    int insertSelective(Cart record);
}