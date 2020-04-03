package com.SE370.Cougar.Roomie.controller.components;

import com.SE370.Cougar.Roomie.controller.services.MessageService;
import com.SE370.Cougar.Roomie.model.DTO.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
// This class is 'websocket' scoped meaning that for every websocket session, a new version of this class will be created.
public class ChatComponent {

    @Autowired
    MessageService messageService;

    private Optional<Integer> thisUserID;
    private Optional<String> thisUserName;
    private Optional<Integer> otherUserID;
    private Optional<String> otherUserName;
    private Optional<Integer> convID;

    // This function should be the first method called by the controller, it will initialize everything accordingly
     public List<Message> getOldMessages() {
        thisUserID.ifPresent(thisUser -> {
            otherUserName.ifPresent(otherUser -> {
                this.otherUserID = messageService.getUserID(otherUser);
                otherUserID.ifPresent(otherID -> {
                    this.convID = Optional.of(messageService.getConversationID(thisUser, otherID));
                });
            });
        });

        // Final check just to make sure values got initialized correctly
        if (convID.isPresent()) {
            return messageService.getOldMessages(convID.get());
        } else {
            return Collections.emptyList();
        }
    }

    public void saveMessage(Message msg) {
        convID.ifPresent(conversation -> {
            thisUserName.ifPresent(senderName -> {
                msg.setSender(senderName);
                messageService.saveMessage(msg, conversation);
            });
        });
    }

    public void setThisUserID(Optional<Integer> thisUserID) {
        this.thisUserID = thisUserID;
    }

    public Optional<String> getThisUserName() {
        return thisUserName;
    }

    public void setThisUserName(Optional<String> thisUserName) {
        this.thisUserName = thisUserName;
    }

    public Optional<String> getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(Optional<String> otherUserName) {
        this.otherUserName = otherUserName;
    }
}
