package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.entities.Conversation;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.ConversationRepo;
import com.SE370.Cougar.Roomie.model.repositories.MessageRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import com.SE370.Cougar.Roomie.view.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    ConversationRepo conversationRepo;
    @Autowired
    UserRepo userRepo;

    public List<Message> getOldMessages (int convID) {
         List<com.SE370.Cougar.Roomie.model.entities.Message> unconverted = messageRepo.findAllByConversationId(convID);
         // Check if there's messages in the list, if so convert the list to DTO
         if (!unconverted.isEmpty()) {
             return unconverted.stream()
                     .map(msg -> convertFromEntity(msg))
                     .collect(Collectors.toList());
         } else {
             return Collections.emptyList();
         }
    }

    public Optional<Integer> getUserID(String userName) {
        // find user ID with username
        return userRepo.findByUserName(userName)
                .map(User::getId);
    }

    public int getConversationID (int user1, int user2) {
        Optional<Integer> possibleConv = conversationRepo.findByUserIdAndRecieverId(user1, user2)
                .map(Conversation::getConversationId);

        if (!possibleConv.isPresent()) {
            possibleConv = conversationRepo.findByUserIdAndRecieverId(user2, user1)
                    .map(Conversation::getConversationId);
        }

        return possibleConv.orElseGet(() -> newConversation(user1, user2));

    }

    public void saveMessage(Message msg, int convID) {
        com.SE370.Cougar.Roomie.model.entities.Message newMsg = convertToEntity(msg);
        newMsg.setConversationId(convID);
        messageRepo.save(newMsg);
    }

    public int newConversation(int userID, int otherID) {
        Conversation newConvo = new Conversation();
        newConvo.setUserId(userID);
        newConvo.setRecieverId(otherID);
        conversationRepo.saveAndFlush(newConvo);
        return newConvo.getConversationId();

    }

    // Utility functions
    // Convert from DTO to Entity Message
    //TODO: Clean these implementations up
    private com.SE370.Cougar.Roomie.model.entities.Message convertToEntity(Message msg) {
        com.SE370.Cougar.Roomie.model.entities.Message converted = new com.SE370.Cougar.Roomie.model.entities.Message();
        converted.setSenderName(msg.getSender());
        converted.setMessage(msg.getContent());
        return converted;
    }
    // Convert Entity Message to DTO Message
    private Message convertFromEntity(com.SE370.Cougar.Roomie.model.entities.Message msg) {
        Message converted = new Message();
        converted.setContent(msg.getMessage());
        converted.setType(Message.MessageType.CHAT);
        converted.setSender(msg.getSenderName());
        return converted;
    }

}
