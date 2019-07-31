package com.neword.demo.controllers;

import com.neword.demo.models.User;
import com.neword.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){

        return "home/login";
    }
    @PostMapping("/signin")
    public String login1(HttpServletRequest request, HttpSession session){

        session.setAttribute("last_url", request.getParameter("referer"));
        System.out.println(request.getParameter("referer"));
        return "home/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpSession httpSession){

        HttpSession session= request.getSession();
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/registration")
    public String registration(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "home/registration";
    }
    @PostMapping("/registration")
    public String saveUser(@Valid User user, BindingResult bindingResult, HttpSession session, Model model) throws MalformedURLException {
        User userExists=userService.findUserByEmail(user.getEmail());
        System.out.println(user.getEmail());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "There is already a user registered with the email provided");
            return "home/registration";
        }else {
            userService.saveUser(user);
        }
        model.addAttribute("successMessage","You have been registered successfully");
        return "home/login";

    }

    @GetMapping("/admin")
    public String homeSuccess(HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user=userService.findUserByEmail(auth.getName());
        session.setAttribute("userId",user.getId());

        return "redirect:"+session.getAttribute("last_url");

    }
}
