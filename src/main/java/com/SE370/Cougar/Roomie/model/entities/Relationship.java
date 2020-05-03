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




/*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Relationship(){};
    public Relationship(int user1_id, int user2_id, int action_user_id, int status, User user){
        this.setUser_one_id(user1_id);
        this.setUser_two_id(user2_id);
        this.setStatus(status);
        this.setAction_user_id(action_user_id);
        this.setUser(user);
    };
    */

/*
    public int getUser_one_id() {
        return this.user_one_id;
    }

    public long getRelationship_id() {
        return this.id;
    }

    public void setUser_one_id(int user_one_id) {
        this.user_one_id = user_one_id;
    }

    public int getUser_two_id() {
        return user_two_id;
    }

    public void setUser_two_id(int user_two_id) {
        this.user_two_id = user_two_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAction_user_id() {
        return action_user_id;
    }

    public void setAction_user_id(int action_user_id) {
        this.action_user_id = action_user_id;
    }
 */
}
