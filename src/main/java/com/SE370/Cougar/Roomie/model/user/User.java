package com.SE370.Cougar.Roomie.model.user;

import javax.persistence.*;

@Entity
@Table(name = "users") // just to keep things looking uniform in the database
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // Tell spring to handle generation
    private long id;
    private long answerID; // connects to answer table
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int gender; // 0 is default (unknown), 1 for Male, 2 for Female
    private String email; // empty by default
    private boolean active; // account flag

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(long answerID) {
        this.answerID = answerID;
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
}