package com.HungSocial.Server.DTO.Message;

public class ChatMessageDTO {
   private Integer senderId;
   private Integer receiverId;
   private String chatId;
   private String content;
public Integer getSenderId() {
    return senderId;
}
public void setSenderId(Integer senderId) {
    this.senderId = senderId;
}
public Integer getReceiverId() {
    return receiverId;
}
public void setReceiverId(Integer receiverId) {
    this.receiverId = receiverId;
}
public String getChatId() {
    return chatId;
}
public void setChatId(String chatId) {
    this.chatId = chatId;
}
public String getContent() {
    return content;
}
public void setContent(String content) {
    this.content = content;
}
public ChatMessageDTO(Integer senderId, Integer receiverId, String chatId, String content) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.chatId = chatId;
    this.content = content;
}
public ChatMessageDTO() {
}
}
