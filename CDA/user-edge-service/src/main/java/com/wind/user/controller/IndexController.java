package com.wind.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String getIndex(){
        return "CDA";
    }

    @RequestMapping("page")
    public String getChartPage(@RequestParam("type") String type,Model model){
        List<String> typeList = new ArrayList<>();
        typeList.add("beijing");
        typeList.add("china");
        typeList.add("globe");
        if(typeList.indexOf(type.toLowerCase()) != -1)
            model.addAttribute("type", type);
        else
            return "WRONG";
        return "ChartPage";
    }

    @RequestMapping("/beijing")
    public String getBeijingPage(){ return "Beijing";}

    @RequestMapping("/china")
    public String getChinaPage(){ return "China";}

    @RequestMapping("/globe")
    public String getGlobePage(){ return "Globe";}

    @RequestMapping("/demo")
    public String getDemo(@RequestParam("type") String type, Model model){
        model.addAttribute("hi","helloworld");
        return "demo";
    }

}
