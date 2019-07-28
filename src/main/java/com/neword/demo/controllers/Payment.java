package com.neword.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Payment {

    @GetMapping(value="/payment")
    public String getPaymentPage()
    {
        return "payment/payment";
    }
    @GetMapping(value="/payment_success")
    public String paymentSuccess() // the redirect page
    {
        return "payment/paymentSuccess";
    }
}
