package com.daesy.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    // matches the property on the other class
//    @OneToMany()
//    List<ApplicationUser> user;

//    public List<ApplicationUser> getUsers(){
//        return this.user;
//    }

    String username;
    String password;
    String firstName;
    String lastName;
    String dayOfBirth;
    String bio;


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

    public long getId() {
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
