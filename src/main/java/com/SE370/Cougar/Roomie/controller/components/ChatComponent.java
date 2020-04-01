package com.SE370.Cougar.Roomie.controller.components;


import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.entities.Message;
import com.SE370.Cougar.Roomie.model.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatComponent {
    @Autowired
    MessageRepo messageRepo;

    public String UserName;
    public Optional<Integer> convID;
    public Optional<Integer> userID;

    public List<com.SE370.Cougar.Roomie.view.Message> getOldMessages () throws RuntimeException {
        convID.orElseThrow(() -> new RuntimeException("No Conversation ID supplied"));
        userID.orElseGet(() -> fillData()); // if userID is empty

        List<Message> unconverted = messageRepo.findAllByConversationId(convID.get());

        // mapping each entity message into client compatible message
        return unconverted.stream()
                .map(msg -> convertFromEntity(msg))
                .collect(Collectors.toList());
    }

    private int fillData() {
        return (((CustomUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())
                .getUser_id());
    }

    // To convert Entities from the DB into our client compatible messages
    private com.SE370.Cougar.Roomie.view.Message convertFromEntity(Message msg) {
        com.SE370.Cougar.Roomie.view.Message converted = new com.SE370.Cougar.Roomie.view.Message();
        converted.setType(com.SE370.Cougar.Roomie.view.Message.MessageType.CHAT);
        converted.setContent(msg.getMessage());
        converted.setSender(msg.getSenderName());
        return converted;
    }

    public void setConvID(int convID) {
        this.convID = Optional.of(convID); // need to wrap in an optional
    }
}
