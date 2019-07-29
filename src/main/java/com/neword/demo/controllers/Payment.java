package com.neword.demo.controllers;

import com.neword.demo.models.Article;
import com.neword.demo.models.User;
import com.neword.demo.repositories.ArticleRepository;
import com.neword.demo.repositories.UserRepository;
import com.neword.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class Payment {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @PostMapping(value="/payment")
    public String getPaymentPage(HttpServletRequest request, Model model)
    {
        Long years = Long.parseLong(request.getParameter("years"));
        double amount = 331.2;
        if(years==4) amount = 441.6;
        else if(years==5) amount = 552;
        model.addAttribute("amount",amount);
        return "payment/payment";
    }
    @GetMapping(value="/payment_success")
    public String paymentSuccess(Model model) // the redirect page
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user=userService.findUserByEmail(auth.getName());
        model.addAttribute("articles",user.getArticles());
        return "payment/paymentSuccess";
    }
    @PostMapping(value="/payment_save")
    public String paymentSave(HttpServletRequest request)
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findUserByEmail(auth.getName());
        Long time = System.currentTimeMillis();
        Article article =new Article("Ssl Certificates",Double.parseDouble(request.getParameter("price")),new Date(time),request.getParameter("years"));
        article.setUser(user);
        List<Article> articles = new ArrayList<Article>();
        articles.add(article);
        user.setArticles(articles);
        userRepository.save(user);
        articleRepository.save(article);
        return "redirect:/payment_success";
    }
}
