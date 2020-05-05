package com.SE370.Cougar.Roomie.model.DTO;

import com.SE370.Cougar.Roomie.model.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String firstName;
    private String lastName;
    private int matchScore;
    private int gender;
    private int user_id;
    private String userName;
    private String password;
    private String email;
    private String major;
    private String headline;
    private boolean active;
    private int answerId;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.matchScore = user.getMatchScore();
        this.gender = user.getGender();
        this.user_id = user.getId();
        this.answerId = user.getAnswer_id();
        this.userName = user.getUserName();
        this.major = user.getMajor();
        this.headline = user.getHeadline();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")); // Hard Coded for now

    }


    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMatchScore() {
        return matchScore;
    }
    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public int getGender() { return gender; }
    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAnswerId() { return answerId; }
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getHeadline() { return headline; }
    public void setHeadline(String headline) { this.headline = headline; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    @Override
    public boolean isEnabled() {
        return active;
    }

}
