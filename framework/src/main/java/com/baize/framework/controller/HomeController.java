package com.baize.framework.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lubinjia
 * @create 2020/4/18 0:15
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
