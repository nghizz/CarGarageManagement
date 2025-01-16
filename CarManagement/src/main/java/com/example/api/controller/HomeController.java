package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/Home")
    public String home() {
        return "Home/Home";
    }
    
    @GetMapping("/CustomerHome")
    public String customerHome() {
        return "CustomerHome/CustomerHome"; 
    }
}