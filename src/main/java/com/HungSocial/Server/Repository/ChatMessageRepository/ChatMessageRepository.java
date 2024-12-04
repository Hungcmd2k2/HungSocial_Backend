package com.HungSocial.Server.Repository.ChatMessageRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.ChatMessage.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
     @Query("SELECT c FROM ChatMessage c WHERE c.chatId = :chatId ORDER BY c.timestamp ASC")
    List<ChatMessage> findMessagesByChatId(@Param("chatId") String chatId);
}