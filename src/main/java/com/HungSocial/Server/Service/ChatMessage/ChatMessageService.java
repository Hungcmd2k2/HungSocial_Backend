package com.HungSocial.Server.Service.ChatMessage;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.DTO.Message.ChatMessageDTO;
import com.HungSocial.Server.Entity.ChatMessage.ChatMessage;
import com.HungSocial.Server.Repository.ChatMessageRepository.ChatMessageRepository;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessRepo;

    public ChatMessage saveMess(ChatMessageDTO chatDTO){

        ChatMessage newChat = new ChatMessage();

        newChat.setSenderId(chatDTO.getSenderId());
        newChat.setReceiverId(chatDTO.getReceiverId());
        newChat.setContent(chatDTO.getContent());
        newChat.setTimestamp(LocalDateTime.now().withSecond(0).withNano(0));
        newChat.setChatId(chatDTO.getChatId());

        return chatMessRepo.save(newChat);
    }

    //lấy hết tin nhắn của phòng đó

    public List<ChatMessage> getAllMessForRoomId(String chatID){
        return chatMessRepo.findMessagesByChatId(chatID);
    }
}
