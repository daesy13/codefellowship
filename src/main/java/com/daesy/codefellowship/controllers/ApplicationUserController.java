package com.daesy.codefellowship.controllers;

import com.daesy.codefellowship.applicationUsers.ApplicationUser;
import com.daesy.codefellowship.applicationUsers.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    // this code helps to start the SALT & HASH of the password
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password, String firstName, String lastName,
                                                 String dayOfBirth, String bio){
        // System.out.println("You are adding a user");
        // make the user AND here we activate the salt and hash for the password
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName
                , dayOfBirth, bio);

        // save the user to db
        applicationUserRepository.save(newUser);

        // maybe autologin?
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // send them back home
        return new RedirectView("/myprofile");
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    // Principal p is the current user
    @GetMapping("/users/{id}")
    public String showUserDetails(@PathVariable long id, Principal p, Model m){
        ApplicationUser theUser = applicationUserRepository.findById(id).get();
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());


        // set attribute on Model
        m.addAttribute("usernameWeAreVisiting", theUser);
//        m.addAttribute("userIdWeAreVisiting", theUser.id);
//        m.addAttribute("userWeAreVisiting", theUser);
        m.addAttribute("principalName", p.getName());
        m.addAttribute("loggedInUser", loggedInUser);
        return "public-view";
    }

    @GetMapping("/myprofile")
    public String showMyProfile(Principal p, Model m){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("user", loggedInUser);
        return "myprofile";
    }

   @GetMapping("/allusers")
    public String showUsers(Principal p, Model m){
        List<ApplicationUser> users = applicationUserRepository.findAll();
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        if (users.contains(loggedInUser)) {
            users.remove(loggedInUser);
        }
        m.addAttribute("users", users);
        m.addAttribute("principalName", p.getName());
        return "users";
   }

    @GetMapping("/feed")
    public String showFeed(Principal p, Model m) {
        List<ApplicationUser> users = applicationUserRepository.findAll();
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        if (users.contains(loggedInUser)) {
            users.remove(loggedInUser);
        }
        m.addAttribute("users", users);
        m.addAttribute("principalName", p.getName());
        return "feed";
    }

    @PostMapping("/follow/{id}")
    public RedirectView follow(@PathVariable long id, Principal p, Model m){
        ApplicationUser personToBeFollow = applicationUserRepository.findById(id).get();
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        loggedInUser.PeopleIamFollowing.add(personToBeFollow);
        applicationUserRepository.save(loggedInUser);
        return new RedirectView("/users/"+id);
    }
}
