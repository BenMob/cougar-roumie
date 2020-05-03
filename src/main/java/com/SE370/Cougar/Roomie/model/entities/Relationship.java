package com.SE370.Cougar.Roomie.model.entities;

import javax.persistence.*;

@Entity(name = "Relationship")
@Table(name = "relationships")
public class Relationship {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private int user_id;
    private String username_one;
    private int user_one_status;
    private String username_two;
    private int user_two_status;
    public Relationship(){};

    public Relationship(int user_id, String username_one, int status1, String username_two, int status2) {
        this.user_id = user_id;
        this.username_one = username_one;
        this.user_one_status = status1;
        this.username_two = username_two;
        this.user_two_status = status2;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername_one() {
        return username_one;
    }

    public void setUsername_one(String username_one) {
        this.username_one = username_one;
    }

    public String getUsername_two() {
        return this.username_two;
    }

    public void setUsername_two(String username_two) {
        this.username_two = username_two;
    }

    public int getUser_two_status() {
        return user_two_status;
    }

    public void setUser_two_status(int user_two_status) {
        this.user_two_status = user_two_status;
    }

    public int getUser_one_status() {
        return user_one_status;
    }

    public void setUser_one_status(int user_one_status) {
        this.user_one_status = user_one_status;
    }
}
