package com.SE370.Cougar.Roomie.model.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepo extends JpaRepository<Conversation, Integer> {
    Conversation findByUserIdAndRecieverId(int userId, int recieverId);
}
