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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class Payment {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping(value="/payment")
    public String getPaymentPage(@RequestParam String years, Model model)
    {
        Long years1 = Long.parseLong(years);
        double amount = 331.2;
        if(years1==4) amount = 441.6;
        else if(years1==5) amount = 552;
        model.addAttribute("amount",amount);
        List<String> countries = new ArrayList<String>();
        String[] isoCountries =Locale.getISOCountries();

        for (String country : isoCountries) {
            Locale locale = new Locale("en", country);
            countries.add(locale.getDisplayCountry());
        }
        model.addAttribute("countries",countries);
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
        System.out.println(request.getParameter(("orderId")));
        System.out.println(request.getParameter(("price")));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findUserByEmail(auth.getName());
        Long time = System.currentTimeMillis();
        Double price = Double.parseDouble(request.getParameter("price"));
        Map<Double,String> priceService = new HashMap<Double,String>();
                priceService.put(357.0, "Ssl Certificates");
                priceService.put(475.68, "Ssl Certificates");
                priceService.put(594.60, "Ssl Certificates");
                priceService.put(215.64, "Cloud Hosting");
                priceService.put(287.52, "Cloud Hosting");
                priceService.put(359.4, "Cloud Hosting");
        priceService.put(287.64, "VPS Hosting");
        priceService.put(383.52, "VPS Hosting");
        priceService.put(479.4, "VPS Hosting");
                Map<Double,String> priceYears = new HashMap<Double,String>();
                priceYears.put(357.0,"3");
                priceYears.put(475.68,"4");
                priceYears.put(594.60,"5");
                // Cloud Hosting
                priceYears.put(215.64, "3");

        priceYears.put(287.52, "4");
        priceYears.put(359.4, "5");
        //VPS HOSTING
        priceYears.put(287.64, "3");
        priceYears.put(383.52, "4");
        priceYears.put(479.4, "5");
        System.out.println(price);
        System.out.println(priceService.toString());
        System.out.println(priceYears.toString());
        Article article =new Article(priceService.get(price),price,new Date(time),priceYears.get(price),time.toString());
        article.setUser(user);
        articleRepository.save(article);
        return "redirect:/payment_success";
    }
}
