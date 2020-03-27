package com.SE370.Cougar.Roomie.model.message;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;
    private int conversationId;
    private int originUserId;
    private String message;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timeStamp; // cannot be changed


    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public long getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(int originUserId) {
        this.originUserId = originUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
