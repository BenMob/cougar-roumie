package com.SE370.Cougar.Roomie.model.DTO;

// This is to hold non sensitive user info to be sent to client
public class UserInfo {
    private String userName;
    private int id;
    private int matchScore;

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

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }
}
