package com.example.fliter.controller;

import com.example.fliter.utils.LoginInfoUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author do
 */
@RestController

public class HelloController {
    @RequestMapping
    public String hello() {
        return "hello:" + LoginInfoUtils.getLoginInfo().getLoginName();
    }
}
