package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.entities.Conversation;
import com.SE370.Cougar.Roomie.model.repositories.ConversationRepo;
import com.SE370.Cougar.Roomie.model.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepo MessageRepo;
    @Autowired
    private ConversationRepo ConversationRepo;

    public int getConversationID (int user1, int user2) {
        Optional<Conversation> convo = ConversationRepo.findByUserIdAndRecieverId(user1, user2);

        if (!convo.isPresent()) {
            convo = ConversationRepo.findByUserIdAndRecieverId(user2, user1);
        }

        return convo.map(id -> id.getConversationId()).orElse(0);
    }

    public List<String> getMessageByConversation(int id) {

        return MessageRepo.findAllByConversationId(id)
                .stream()
                .map(msg -> msg.getMessage())
                .collect(Collectors.toList());
    }



}
