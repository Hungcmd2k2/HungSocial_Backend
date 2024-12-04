package com.HungSocial.Server.Service.ChatRoom;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.DTO.ChatRoomIdDTO.ChatRoomIdDTO;
import com.HungSocial.Server.Entity.ChatRoomID.ChatRoomID;
import com.HungSocial.Server.Repository.ChatRoomRepository.ChatRoomRepository;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepo;
    public ChatRoomID createRoomID(ChatRoomIdDTO chatRoomDTO){
          
        ChatRoomID newRoom= new ChatRoomID();

        newRoom.setUseroneId(chatRoomDTO.getUseroneId());
        newRoom.setUsertwoId(chatRoomDTO.getUsertwoId());
        newRoom.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));

        return chatRoomRepo.save(newRoom);
    }
    //Kiểm tra xem phòng có chưa
    public Optional<ChatRoomID> checkChatRoom(Integer useroneId, Integer usertwoId) {
        return chatRoomRepo.findChatRoomByUsers(useroneId, usertwoId);
    }
}
