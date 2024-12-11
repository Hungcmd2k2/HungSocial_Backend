package com.HungSocial.Server.Controller.Notifications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.NotificationRequest.NotificationRequest;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Entity.Notification.NotificationEntity;
import com.HungSocial.Server.Service.Notification.NotificationService;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

     @PostMapping("/send")
    public ResponseEntity<ApiResponse<Object>> sendNotification(@RequestBody NotificationRequest request) {
        // Gửi thông báo tới client qua "/topic/notifications"
        messagingTemplate.convertAndSend("/topic/notifications", request.getMessage());
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.OK.value(),
                "Gửi thông báo thành công",
                request.getMessage()
        );
        return ResponseEntity.ok(response);
    }

    // @PostMapping("/save")
    public ResponseEntity<ApiResponse<Object>> saveNotification(String topic,NotificationEntity notifiEntity){
                NotificationEntity notifi = notificationService.create(notifiEntity);
                if(notifi!=null){
                    messagingTemplate.convertAndSend("/topic/"+topic, notifiEntity);

                    ApiResponse<Object> response = new ApiResponse<>(
                        "error",
                        HttpStatus.OK.value(),
                        "Lưu thông báo thành công",
                        notifi
                );
                return ResponseEntity.ok(response); 
                }else{
                    ApiResponse<Object> response = new ApiResponse<>(
                        "error",
                        HttpStatus.NOT_FOUND.value(),
                        "Ko lưu thông báo được",
                        null
                );
                return ResponseEntity.ok(response);  
                }
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ApiResponse<Object>> getMethodName(@PathVariable Integer userid) {
       List<NotificationEntity> notifi = notificationService.getNotiByUserid(userid);

       if(!notifi.isEmpty()){
        ApiResponse<Object> response = new ApiResponse<>(
                        "error",
                        HttpStatus.OK.value(),
                        "Danh sách thông báo",
                        notifi
                );
                return ResponseEntity.ok(response); 
       }
       else{
        ApiResponse<Object> response = new ApiResponse<>(
                        "error",
                        HttpStatus.NOT_FOUND.value(),
                        "Danh sách thông báo",
                        null
                );
                return ResponseEntity.ok(response); 
       }
    }
    
}
