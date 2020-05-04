package com.SE370.Cougar.Roomie.model.DTO;

import java.util.Optional;

// This is to hold non sensitive user info to be sent to client
public class UserInfo {
    private String userName;
    private String name;
    private int id;
    private int matchScore;
    private String bio;
    private Optional<FileTypeData> image;

    public UserInfo(){};

    public UserInfo(String userName, String name, int id, int matchScore, String bio, Optional<FileTypeData> image) {
        this.userName = userName;
        this.name = name;
        this.id = id;
        this.matchScore = matchScore;
        this.bio = bio;
        this.image = image;
    }

    // This is used by extended classes
    public UserInfo(UserInfo copy) {
        this.userName = copy.userName;
        this.name = copy.name;
        this.id = copy.id;
        this.matchScore = copy.matchScore;
        this.bio = copy.bio;
        this.image = copy.image;
    }

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public Optional<FileTypeData> getImage() {
        return image;
    }
    public void setImage(Optional<FileTypeData> image) {
        this.image = image;
    }
}
