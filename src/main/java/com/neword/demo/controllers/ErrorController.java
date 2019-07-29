package com.neword.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public String errorPage(HttpServletRequest request, Model model){
        int httpErrorCode=getErrorCode(request);
        String errorMessage="";
        switch (httpErrorCode){
            case 400: {
                errorMessage = "Code d'Erreur: 400.  Bad request !!!!";
                break;
            }
            case 401: {
                errorMessage = "Code d'Erreur: 401. Unauthorized request!!!!";
                break;
            }
            case 404: {
                errorMessage = "Code d'Erreur: 404. Information not found";
                break;
            }
            case 500: {
                errorMessage = "Code d'Erreur: 500.   Server error found, our technical team is working on it";
                break;
            }
        }
        model.addAttribute("errorMessage",errorMessage);
        return "home/errorPage";
    }
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
