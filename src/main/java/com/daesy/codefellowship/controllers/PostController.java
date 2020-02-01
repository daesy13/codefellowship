package com.daesy.codefellowship.controllers;

import com.daesy.codefellowship.applicationUsers.ApplicationUser;
import com.daesy.codefellowship.applicationUsers.ApplicationUserRepository;
import com.daesy.codefellowship.post.Post;
import com.daesy.codefellowship.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/post")
    public RedirectView createNewPost(String body, Principal p){
        Post newPost = new Post(body);
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        newPost.applicationUser = loggedInUser;
        postRepository.save(newPost);
        return new RedirectView("/myprofile");
    }
}
