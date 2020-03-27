package com.SE370.Cougar.Roomie.model.message;

import javax.persistence.*;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int conversationId;
    private int userId;
    private int recieverId;


    public Conversation() {}

    public Conversation(int user, int reciever) {
        this.userId = user;
        this.recieverId = reciever;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }
}
