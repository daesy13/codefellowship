package com.daesy.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class HomeController {

    // I want to show the username on the main page
    // Principal means logged in user
    // Model m sends the data to htmal and thymeleaf
    @GetMapping("/")
    public String getHome(Principal p, Model m){
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
        return "home";
    }

}
