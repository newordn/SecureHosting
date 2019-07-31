package com.neword.demo.controllers;


import com.neword.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
class Home {
    public Home()
    {

    }
    @GetMapping(value="/")
    public String getHome(Model model, HttpSession httpSession, HttpServletRequest request)
    {
        User user=new User();
        model.addAttribute("user",user);
        return "home/home";
    }

    @GetMapping(value="/detail/{id}")
    public String getDetail(@PathVariable Long id, Model model)
    {
        model.addAttribute("number",id);return "detail/detail";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession httpSession, HttpServletRequest request){
        httpSession.setAttribute("last_url",request.getHeader("referer"));
        User user=new User();
        model.addAttribute("user",user);
        return "home/about";
    }


    @GetMapping("/services")
    public String services(Model model,  HttpSession httpSession, HttpServletRequest request)
    {
        httpSession.setAttribute("last_url",request.getHeader("referer"));
        User user=new User();
        model.addAttribute("user",user);
        return "home/service";
    }

    @GetMapping("/contact")
    public String contact(Model model,HttpSession httpSession, HttpServletRequest request){
        httpSession.setAttribute("last_url",request.getHeader("referer"));
        User user=new User();
        model.addAttribute("user",user);
        return "home/contact";
    }
}
