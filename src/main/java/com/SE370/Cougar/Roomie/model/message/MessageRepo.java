package com.SE370.Cougar.Roomie.model.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    List<Message> findAllByConversationId(int conversationId); // grabs all messages with that conversation ID
}
