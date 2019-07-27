package com.neword.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/payment")
public class Payment {

    @GetMapping(value="/")
    public String getPaymentPage()
    {
        return "payment/payment";
    }
    @GetMapping(value="/success")
    public String paymentSuccess() // the redirect page
    {
        return "payment/paymentSuccess";
    }
}
