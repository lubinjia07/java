package com.baize.framework.service;

import com.baize.framework.dto.UserInfoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserInfoDto getUserInfo(){
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId("");
        userInfoDto.setUseraName("");
        return userInfoDto;
    }

    public List<UserInfoDto> getUserInfoList(){
        List<UserInfoDto> userInfoDtos = new ArrayList<>();
        UserInfoDto userInfoDto1 = new UserInfoDto();
        userInfoDto1.setUserId("");
        userInfoDto1.setUseraName("");

        UserInfoDto userInfoDto2 = new UserInfoDto();
        userInfoDto2.setUserId("");
        userInfoDto2.setUseraName("");

        userInfoDtos.add(userInfoDto1);
        userInfoDtos.add(userInfoDto2);
        return userInfoDtos;
    }
}
