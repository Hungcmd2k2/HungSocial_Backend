package com.HungSocial.Server.Entity.Notification;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    private String  avatarNotifi;
    private String  usernameNotifi;
    private String  content;
    private LocalDateTime createdAt;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getAvatarNotifi() {
        return avatarNotifi;
    }
    public void setAvatarNotifi(String avatarNotifi) {
        this.avatarNotifi = avatarNotifi;
    }
    public String getUsernameNotifi() {
        return usernameNotifi;
    }
    public void setUsernameNotifi(String usernameNotifi) {
        this.usernameNotifi = usernameNotifi;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
