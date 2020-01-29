package com.daesy.codefellowship.controllers;

import com.daesy.codefellowship.models.ApplicationUser;
import com.daesy.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    // this code helps to start the SALT & HASH of the password
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password){
        System.out.println("You are adding a user");
        // make the user AND here we activate the salt and hash for the password
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password));

        // save the user to db
        applicationUserRepository.save(newUser);

        // send them back home
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }


//    @PostMapping("/login")
//    public RedirectView login(){
//        return new RedirectView("/");
//    }
}
