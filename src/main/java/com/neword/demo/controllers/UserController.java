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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpSession httpSession){

        HttpSession session= request.getSession();
        session.invalidate();
        return "redirect:"+httpSession.getAttribute("last_url");
    }


    @PostMapping("/registration")
    public String saveUser(@Valid User user, BindingResult bindingResult, HttpSession session, Model model) throws MalformedURLException {
        User userExists=userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        URL url=new URL("http://localhost:8080/");
        URL url1=new URL("http://localhost:8080/about");
        URL url2=new URL("http://localhost:8080/contact");
        URL url3=new URL("http://localhost:8080/services");

        if (bindingResult.hasErrors()){
            if (url.equals((URL) session.getAttribute("last_url"))){
                model.addAttribute("error","There is already a user registered with the email provided");
                return "home/home";
            }else if (url1.equals((URL) session.getAttribute("last_url"))){
                model.addAttribute("error","There is already a user registered with the email provided");
                return "home/about";
            }else if (url2.equals((URL) session.getAttribute("last_url"))){
                model.addAttribute("error","There is already a user registered with the email provided");
                return "home/contact";
            }else if (url3.equals((URL) session.getAttribute("last_url"))){
                model.addAttribute("error","There is already a user registered with the email provided");
                return "home/service";
            }else {
                System.out.println("rien a faire");
            }
        }else {
            userService.saveUser(user);
            model.addAttribute("successMessage","You have been registered successfully");
        }
        return "redirect:"+session.getAttribute("last_url");

    }

    @GetMapping("/admin/home")
    public String homeSuccess(HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findUserByEmail(auth.getName());
        session.setAttribute("userId",user.getId());
        return "redirect:"+session.getAttribute("last_url");

    }
}
