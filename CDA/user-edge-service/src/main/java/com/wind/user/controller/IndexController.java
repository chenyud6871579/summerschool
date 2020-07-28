package com.wind.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String getIndex(){
        return "CDA";
    }

    @RequestMapping("/beijing")
    public String getBeijingPage(){ return "Beijing";}

    @RequestMapping("/china")
    public String getChinaPage(){ return "China";}

    @RequestMapping("/globe")
    public String getGlobePage(){ return "Globe";}

}
