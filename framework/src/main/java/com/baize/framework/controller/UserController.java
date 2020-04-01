package com.baize.framework.controller;

import com.baize.framework.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户api")
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;


}
