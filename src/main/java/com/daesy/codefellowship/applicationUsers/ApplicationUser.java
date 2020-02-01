package com.daesy.codefellowship.applicationUsers;

import com.daesy.codefellowship.post.Post;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // matches the property on the other class
//    @OneToMany()
//    List<ApplicationUser> user;

//    public List<ApplicationUser> getUsers(){
//        return this.user;
//    }

    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String dayOfBirth;
    public String bio;

    @OneToMany(mappedBy = "applicationUser")
    public List<Post> posts;

    public List<Post> getPosts(){
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public ApplicationUser(){};

    public ApplicationUser(String username, String password, String firstName, String lastName, String dayOfBirth,
                           String bio){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // return FALSE when we want to disable user ex. if they didn't pay their membership
    @Override
    public boolean isEnabled() {
        return true;
    }

}
