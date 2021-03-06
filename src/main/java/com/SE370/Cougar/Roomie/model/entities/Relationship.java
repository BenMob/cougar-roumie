package com.SE370.Cougar.Roomie.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "relationships")
public class Relationship {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String username1; // Jpa doesn't like underscores for some reason
    private int useronestatus = 0;
    private String username2;
    private int usertwostatus = 0;


    public Relationship(){};

    // Creates and fills usernames
    public Relationship(String username_one, String username_two) {
        this.username1 = username_one;
        this.username2 = username_two;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username_one) {
        this.username1 = username_one;
    }

    public String getUsername2() {
        return this.username2;
    }

    public void setUsername2(String username_two) {
        this.username2 = username_two;
    }

    public int getUser_two_status() {
        return usertwostatus;
    }

    public void setUser_two_status(int user_two_status) {
        this.usertwostatus = user_two_status;
    }

    public int getUser_one_status() {
        return useronestatus;
    }

    public void setUser_one_status(int user_one_status) {
        this.useronestatus = user_one_status;
    }
}
