package com.HungSocial.Server.Controller.Websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.HungSocial.Server.DTO.Message.ChatMessageDTO;
import com.HungSocial.Server.Service.ChatMessage.ChatMessageService;

@Controller
public class WebsocketController {
    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDTO chat(@DestinationVariable String roomId, ChatMessageDTO message) {
        chatMessageService.saveMess(message);
        return new ChatMessageDTO(message.getSenderId(), message.getReceiverId(), message.getChatId(),
                message.getContent());
    }
}
