package com.baize.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lubinjia
 * @create 2020/4/17 1:23
 */
@Controller
public class HomeController {
    //    @RequestMapping(value = {"/","home","index"})
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
