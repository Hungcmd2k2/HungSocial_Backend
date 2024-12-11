package com.HungSocial.Server.Service.Notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.Notification.NotificationEntity;
import com.HungSocial.Server.Repository.Notification.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
     private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void Notification(String topic,String message){
        messagingTemplate.convertAndSend("/topic/" + topic, message);
    }

    public NotificationEntity create(NotificationEntity notifiEntity){
        NotificationEntity notifi = new NotificationEntity();
        notifi.setUserid(notifiEntity.getUserid());
        notifi.setAvatarNotifi(notifiEntity.getAvatarNotifi());
        notifi.setUsernameNotifi(notifiEntity.getUsernameNotifi());
        notifi.setContent(notifiEntity.getContent());
        notifi.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));

       return  notificationRepository.save(notifi);

    }

    public List<NotificationEntity> getNotiByUserid(Integer userid){
        return notificationRepository.findByUserid(userid);

    }
}
