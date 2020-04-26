package com.SE370.Cougar.Roomie.model;

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
    private int gender;
    private int user_id;
    private int profile_image_id;
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
        this.gender = user.getGender();
        this.user_id = user.getId();
        this.profile_image_id = user.getProfile_image_id();
        this.answerId = user.getAnswer_id();
        this.userName = user.getUserName();
        this.major = user.getMajor();
        this.headline = user.getHeadline();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")); // Hard Coded for now

        // This will grab roles from the database however we are statically setting one role for now
        /*this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

         */
    }


    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public int getGender() { return gender; }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAnswerId() { return answerId; }

    public String getMajor() { return major; }

    public void setMajor(String major) { this.major = major; }

    public String getHeadline() { return headline; }

    public void setHeadline(String headline) { this.headline = headline; }

    public int getProfile_Image_Id() {
        return profile_image_id;
    }

    public void setProfile_Image_Id(int profile_Image_Id) {
        this.profile_image_id = profile_image_id;
    }

    public String getEmail() { return email; }
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
