package com.SE370.Cougar.Roomie.model.DTO;

// This is to hold non sensitive user info to be sent to client
public class UserInfo {
    private String userName;
    private int id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
