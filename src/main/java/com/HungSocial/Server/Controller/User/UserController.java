package com.HungSocial.Server.Controller.User;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.Request.Auth.CheckPassword;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.DTO.User.SearchUserDTO;
import com.HungSocial.Server.DTO.User.UserOtherInfor;
import com.HungSocial.Server.DTO.UserDetail.UpdateAvatarDTO;
import com.HungSocial.Server.DTO.UserDetail.UserDetailDTO;
import com.HungSocial.Server.DTO.UserDetail.UserListChat;
import com.HungSocial.Server.Entity.User.User;
import com.HungSocial.Server.Entity.UserDetails.UserDetails;
import com.HungSocial.Server.Service.User.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> getUserById(@PathVariable Integer userId){
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()){
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Lấy dữ liệu người dùng thành công!",
                user 
            );
            return ResponseEntity.ok(response);   
        }else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.NOT_FOUND.value(),
                "Lấy dữ liệu người dùng ko thành công!",
                null 
            );
            return ResponseEntity.ok(response);    
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
       if(createdUser != null ){
         ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Tạo tài khoản thành công!",
            createdUser 
        );
        return ResponseEntity.ok(response);  
       }
       else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "Tạo tài khoản không thành công!",
            null
        );
        return ResponseEntity.ok(response);
       }

    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllUser(){
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Người dùng được tìm thấy",
                users
            );
            return ResponseEntity.ok(response);  
        }
        else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.NOT_FOUND.value(),
                "Hệ thống chưa có tài khoản nào",
                null
            );
            return ResponseEntity.ok(response);
        }
    }
//update mật khẩu user
    @PutMapping("/update_Password")
    public ResponseEntity<ApiResponse<Object>> updatePassUser(@RequestBody CheckPassword checkPassword) {
       User user_update = userService.updateUser(checkPassword.getUserid(), checkPassword.getPassword());

      if(user_update!=null){
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Cập nhật password user thành công",
            user_update
        );
        return ResponseEntity.ok(response);  
      }else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.BAD_REQUEST.value(),
            "Cập nhật password user ko thành công",
            null
        );
        return ResponseEntity.ok(response);  
      }
   
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
public ResponseEntity<ApiResponse<Object>> getUserByEmail(@PathVariable String email) {
    Optional<User> user = userService.getUserByEmail(email);

    if (user.isPresent()) {
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Người dùng được tìm thấy",
            user.get() // Trả về thông tin người dùng nếu có
        );
        return ResponseEntity.ok(response);  
    } else {
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "Người dùng không tồn tại",
            null
        );
        return ResponseEntity.ok(response);
    }
}

@GetMapping("/username/{username}")
public ResponseEntity<ApiResponse<Object>> getUserByUsername(@PathVariable String username) {
    Optional<User> user = userService.getUserByUsername(username);

    if (user.isPresent()) {
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Người dùng được tìm thấy",
            user.get() // Trả về thông tin người dùng nếu có
        );
        return ResponseEntity.ok(response);  
    } else {
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "Người dùng không tồn tại",
            null
        );
        return ResponseEntity.ok(response);
    }
}
// Api tìm user theo keyword
@GetMapping("/search/{keyword}")
public ResponseEntity<ApiResponse<Object>> searchUser(@PathVariable String keyword){
    List<SearchUserDTO> users = userService.getUserByKeyWord(keyword);
    if(!users.isEmpty()){
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Danh sách Người dùng được tìm thấy",
            users
        );
        return ResponseEntity.ok(response);  
    }else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "Không tìm thấy tài khoản nào",
            null
        );
        return ResponseEntity.ok(response);  
    }

}
//userdetaill------------
    
//update detail user
    @PutMapping("/details/update")
    public ResponseEntity<ApiResponse<Object>> updateUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
        UserDetails updateUserDetail = userService.updateUserDetail(userDetailDTO);

      if(updateUserDetail!=null){
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Thêm mới chi tiết người dùng thành công",
            updateUserDetail
        );
        return ResponseEntity.ok(response);  
      }else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.BAD_REQUEST.value(),
            "Chưa có người dùng này",
            null
        );
        return ResponseEntity.ok(response);  
      }
   
}
//Update avatar cho detail user
@PutMapping("/details/update/AVATAR")
    public ResponseEntity<ApiResponse<Object>> updateAvatar(@RequestBody UpdateAvatarDTO updateAvatar) {
        UserDetails updateUserDetail = userService.updateAvater(updateAvatar);

      if(updateUserDetail!=null){
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Cập nhật avatar thành  công",
            updateUserDetail
        );
        return ResponseEntity.ok(response);  
      }else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.BAD_REQUEST.value(),
            "Cập nhật avatar không thành công",
            null
        );
        return ResponseEntity.ok(response);  
      }
   
}
//update ảnh bìa
@PutMapping("/details/update/COVER-PHOTO")
    public ResponseEntity<ApiResponse<Object>> updateCoverPhoto(@RequestBody UpdateAvatarDTO updateAvatar) {
        UserDetails updateUserDetail = userService.updateCoverPhoto(updateAvatar);

      if(updateUserDetail!=null){
        ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Cập nhật ảnh bìa thành  công",
            updateUserDetail
        );
        return ResponseEntity.ok(response);  
      }else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.BAD_REQUEST.value(),
            "Cập nhật ảnh bìa không thành công",
            null
        );
        return ResponseEntity.ok(response);  
      }
   
}


// get info của userdetail
@GetMapping("details/{userid}")
public ResponseEntity<ApiResponse<Object>> getUserDetail(@PathVariable Integer userid) {
    Optional<UserDetails> userDetail = userService.getUserDetail(userid);
    Optional<User> user = userService.getUserById(userid);
    

  if(userDetail!=null){
    UserDetails userdetailResponse = userDetail.get();
    User userRes=user.get();
    
    UserListChat userList= new UserListChat(userdetailResponse.getId(),
    userRes.getId(),userRes.getUsername(),userdetailResponse.getFullname(),
    userdetailResponse.getAvatar(),userdetailResponse.getCoverphoto(),userdetailResponse.getDob(),
    userdetailResponse.getLinksocial(),userdetailResponse.getLovesong(),userdetailResponse.getEducation(),userdetailResponse.getAddress());

    ApiResponse<Object> response = new ApiResponse<>(
        "success",
        HttpStatus.OK.value(),
        "Lấy dữ liệu user thành công",
        userList
    );
    return ResponseEntity.ok(response);  
  }else{
    ApiResponse<Object> response = new ApiResponse<>(
        "error",
        HttpStatus.BAD_REQUEST.value(),
        "Chưa có người dùng này",
        null
    );
    return ResponseEntity.ok(response);  
  }

}
   //get info trang cá nhân của người dùng khác dựa theo username
   @GetMapping("/userOther/{username}")
   public ResponseEntity<ApiResponse<Object>> getUserOther(@PathVariable String username) {
    UserOtherInfor userOtherInfor = userService.getUserOtherInfor(username);

  if(userOtherInfor!=null){
    ApiResponse<Object> response = new ApiResponse<>(
        "success",
        HttpStatus.OK.value(),
        "Lấy dữ liệu user thành công",
        userOtherInfor
    );
    return ResponseEntity.ok(response);  
  }else{
    ApiResponse<Object> response = new ApiResponse<>(
        "error",
        HttpStatus.BAD_REQUEST.value(),
        "Chưa có người dùng này",
        null
    );
    return ResponseEntity.ok(response);  
  }

}
}
