package com.baize.framework.dao;

import com.baize.framework.domain.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}