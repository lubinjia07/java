package com.baize.framework.dao;

import com.baize.framework.domain.Category;

public interface CategoryMapper {
    int insert(Category record);

    int insertSelective(Category record);
}