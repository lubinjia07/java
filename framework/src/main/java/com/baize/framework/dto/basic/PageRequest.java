package com.baize.framework.dto.basic;

import lombok.Data;

@Data
public class PageRequest {
    public Integer pageIndex;

    public Integer pageSize;

    public Integer totalCount;

}
