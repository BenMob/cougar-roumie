package com.SE370.Cougar.Roomie.model.DTO;

public class MatchForm extends UserInfo {
    public enum MessageType {
        INIT,
        REQUEST,
        NEWMATCH,
        DISLIKE,
        LIKE,
        ERROR
    }
    private MessageType type;


    public MatchForm(){};
    public MatchForm(UserInfo old) {
        super(old);
    }

    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
}
