package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.controller.components.ObjectConverter;
import com.SE370.Cougar.Roomie.model.entities.Conversation;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.model.repositories.ConversationRepo;
import com.SE370.Cougar.Roomie.model.repositories.MessageRepo;
import com.SE370.Cougar.Roomie.model.repositories.UserRepo;
import com.SE370.Cougar.Roomie.model.DTO.Message;
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
    @Autowired
    ObjectConverter converter;

    // Utility //
    private int newConversation(int userID, int otherID) {
        Conversation newConvo = new Conversation(userID, otherID);
        conversationRepo.saveAndFlush(newConvo);
        return newConvo.getConversationId();
    }

    public List<Message> getOldMessages (int convID) {
         return messageRepo.findAllByConversationId(convID).stream()
                 .map(converter::convertFromEntity)
                 .collect(Collectors.toList());
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
        com.SE370.Cougar.Roomie.model.entities.Message newMsg = converter.convertToEntity(msg);
        newMsg.setConversationId(convID);
        messageRepo.save(newMsg);
    }


}
