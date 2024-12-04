package com.HungSocial.Server.Controller.Message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Entity.ChatMessage.ChatMessage;
import com.HungSocial.Server.Service.ChatMessage.ChatMessageService;

@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/MessRoom/{chatId}")
    public ResponseEntity<ApiResponse<Object>> getMess(@PathVariable String chatId){
        List<ChatMessage> listChat =  chatMessageService.getAllMessForRoomId(chatId);
        if(!listChat.isEmpty()){
             ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Danh sách tin nhắn",
                    listChat
            );
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.BAD_REQUEST.value(),
                "Danh sách tin nhắn rỗng",
                listChat
        );
        return ResponseEntity.ok(response);
        }
    }
}
