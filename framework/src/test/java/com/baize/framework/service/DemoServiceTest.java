package com.baize.framework.service;

import com.baize.framework.dto.DemoRequestDto;
import com.baize.framework.dto.DemoResponseDto;
import org.apache.logging.log4j.util.Strings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class DemoServiceTest extends BaseTest {
    @Autowired
    private DemoService demoService;

    @Test
    void getDemo() {
        Assert.assertEquals(demoService.getDemo(), Strings.EMPTY);
    }

    @Test
    void getList() {
        DemoRequestDto requestDto = new DemoRequestDto();
        List<DemoResponseDto> list = demoService.getList(requestDto);

    }

}