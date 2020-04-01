package com.baize.framework.service;

import com.baize.framework.dto.DemoRequestDto;
import com.baize.framework.dto.DemoResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {
    @Value("${}")
    private String str;

    public String getDemo(){
        return "123";
    }

    public List<DemoResponseDto> getList(DemoRequestDto requestDto){
        List<DemoResponseDto> list = new ArrayList<>();
        DemoResponseDto demoResponseDto1 = new DemoResponseDto();
        demoResponseDto1.setUserId("001");
        demoResponseDto1.setUserName("小红");
        demoResponseDto1.setAge(0);

        DemoResponseDto demoResponseDto2 = new DemoResponseDto();
        demoResponseDto2.setUserId("002");
        demoResponseDto2.setUserName("小明");
        demoResponseDto2.setAge(0);

        DemoResponseDto demoResponseDto3 = new DemoResponseDto();
        demoResponseDto3.setUserId("003");
        demoResponseDto3.setUserName("小黄");
        demoResponseDto3.setAge(0);

        list.add(demoResponseDto1);
        list.add(demoResponseDto2);
        list.add(demoResponseDto3);
        return list;
    }

}
