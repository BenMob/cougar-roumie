package com.SE370WebApp.cougarroomie.models;

public class Person {
    private String emailAddress;
    private String password;
    private String name;

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }

}
