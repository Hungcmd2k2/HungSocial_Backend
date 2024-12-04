package com.HungSocial.Server.Entity.ChatRoomID;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="chat_rooms")
public class ChatRoomID {
    @Id
    private String id = UUID.randomUUID().toString();
    private  Integer useroneId;
    private Integer usertwoId;
    public Integer getUseroneId() {
        return useroneId;
    }
    public void setUseroneId(Integer useroneId) {
        this.useroneId = useroneId;
    }
    public Integer getUsertwoId() {
        return usertwoId;
    }
    public void setUsertwoId(Integer usertwoId) {
        this.usertwoId = usertwoId;
    }
    private LocalDateTime createdAt;
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
   
}
