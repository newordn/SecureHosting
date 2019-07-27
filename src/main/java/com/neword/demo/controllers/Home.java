package com.neword.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class Home {
    public Home()
    {

    }
    @GetMapping(value="/")
    public String getHome()
    {
        return "home/home";
    }

    @GetMapping(value="/detail/{id}")
    public String getDetail(@PathVariable Long id)
    {
        return "detail/detail";
    }

    @GetMapping("/about")
    public String about(){
        return "home/about";
    }


    @GetMapping("/services")
    public String services(){
        return "home/service";
    }
}
