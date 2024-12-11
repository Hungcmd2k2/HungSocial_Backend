package com.HungSocial.Server.Controller.ChatRoom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.ChatRoomIdDTO.ChatRoomIdDTO;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Entity.ChatRoomID.ChatRoomID;
import com.HungSocial.Server.Service.ChatRoom.ChatRoomService;



@RestController
@RequestMapping("/api")
public class ChatRoomController {
    @Autowired ChatRoomService chatRoomService;

    @PostMapping("/checkRoomId")
    public ResponseEntity<ApiResponse<Object>> checkRoomID(@RequestBody ChatRoomIdDTO chatRoomIdDTO ) {
        Optional<ChatRoomID> room = chatRoomService.checkChatRoom(chatRoomIdDTO.getUseroneId(), chatRoomIdDTO.getUsertwoId()); // Chỉnh sửa đối số

        if (room.isPresent()) {  // Nếu phòng đã tồn tại
            String ID_Room = room.get().getId();
            ApiResponse<Object> response = new ApiResponse<>(
                    "success", // Thay "error" bằng "success"
                    HttpStatus.OK.value(),
                    "Room đã tồn tại.", // Cập nhật thông điệp
                    ID_Room
            );
            return ResponseEntity.ok(response);
        } else {  // Nếu phòng không tồn tại
            ApiResponse<Object> response = new ApiResponse<>(
                    "error", // Giữ lại "error" cho trường hợp lỗi
                    HttpStatus.BAD_REQUEST.value(),  // Sử dụng mã lỗi 400 khi không tìm thấy phòng
                    "Room chưa có.",  // Thông điệp lỗi
                    null
            );
            return ResponseEntity.ok(response);
        }
        
    }

    //Thêm phòng nếu chưa có
    @PostMapping("/Room/create")
    public ResponseEntity<ApiResponse<Object>> createRoomID(@RequestBody ChatRoomIdDTO chatRoomIdDTO ) {
        ChatRoomID room = chatRoomService.createRoomID(chatRoomIdDTO);
        
        if (room !=null) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "succsess",
                    HttpStatus.OK.value(),
                    "Tạo room thành công",
                    room
            );
            return ResponseEntity.ok(response);
        }else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.BAD_REQUEST.value(),
                "Tạo room chưa thành công ",
                null
        );
        return ResponseEntity.ok(response);
        }
    }
    
}
