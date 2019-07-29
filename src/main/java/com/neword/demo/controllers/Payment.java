package com.neword.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Payment {

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
    public String paymentSuccess() // the redirect page
    {
        return "payment/paymentSuccess";
    }
}
