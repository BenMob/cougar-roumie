package com.SE370.Cougar.Roomie.model.entities;

import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.Profile;
import com.SE370.Cougar.Roomie.model.DTO.RegistrationForm;

import javax.persistence.*;

@Entity
@Table(name = "users") // just to keep things looking uniform in the database
public class User{
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // Tell spring to handle generation
    private int id;
    private int answer_id; // connects to answer table
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String major;
    private String headline;
    private int gender; // 0 is default (unknown), 1 for Male, 2 for Female
    private String email; // empty by default
    private boolean active; // account flag
    //private long visits; // number of times a user has logged in


    public User() {}

    // Task: Creates a User Entity
    // Parameter: CustomUserDetails Object
    public User(CustomUserDetails customUser){
            setId(customUser.getUser_id());
            setAnswer_id(customUser.getAnswerId());
            setFirstName(customUser.getFirstName());
            setLastName(customUser.getLastName());
            setGender(customUser.getGender());
            setUserName(customUser.getUsername());
            setPassword(customUser.getPassword());
            setHeadline(customUser.getHeadline());
            setMajor(customUser.getMajor());
            setEmail(customUser.getEmail());
            setActive(customUser.isEnabled());

    }

    // Task: Copy constructor that registers user for the first time
    // Parameter: RegistrationForm object
    public User(RegistrationForm form){
            setUserName(form.getUser_name());
            setEmail(form.getEmail());
            setPassword(form.getPassword());
            setActive(true);
    }

    // Task: registers profile information
    public void registerProfileInfo(Profile form){
            setFirstName(form.getFirst_name());
            setLastName(form.getLast_name());
            setGender(form.getGender());
            setMajor(form.getMajor());
            setHeadline(form.getHeadline());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answerID) {
        this.answer_id = answerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}