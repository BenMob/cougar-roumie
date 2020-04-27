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
}
