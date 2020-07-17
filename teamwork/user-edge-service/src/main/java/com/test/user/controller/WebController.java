package com.test.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/index")
    public String getIndex(){
        return "spiderweb";
    }
}
