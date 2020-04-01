package com.baize.framework.dao;

import com.baize.framework.domain.PayInfo;

public interface PayInfoMapper {
    int insert(PayInfo record);

    int insertSelective(PayInfo record);
}