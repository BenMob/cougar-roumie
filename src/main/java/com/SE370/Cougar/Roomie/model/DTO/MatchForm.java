package com.SE370.Cougar.Roomie.model.DTO;

public class MatchForm {

    public enum MessageType {
        INIT,
        REQUEST,
        NEWMATCH,
        DISLIKE,
        LIKE,
        ERROR
    }
    private String userName;
    private MessageType type;
    private String name;
    private String bio;

    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
}
