package com.SE370.Cougar.Roomie.model.DTO;

public class Message {
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
    }

    private MessageType type;
    private String content;
    private String sender;
    private String reciever;

    public Message() {};
    public Message(String sender, String reciever) {
        this.sender = sender;
        this.reciever = reciever;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}