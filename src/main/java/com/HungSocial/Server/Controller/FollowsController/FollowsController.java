package com.HungSocial.Server.Controller.FollowsController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Entity.Follows.Follows;
import com.HungSocial.Server.Service.FollowsService.FollowsService;



@RestController
@RequestMapping("/api")
public class FollowsController {
    @Autowired
    private FollowsService followsService;
  
     @PostMapping("/follow")
    public ResponseEntity<ApiResponse<Object>> follow(@RequestBody Follows follows) {
        Follows follow = followsService.followUser(follows.getFollowerId(), follows.getFollowingId());
       if(follow!= null ){
         ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Follow thành công!",
            follow 
        );
        return ResponseEntity.ok(response);  
       }
       else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "Follow không thành công!",
            null
        );
        return ResponseEntity.ok(response);
       }

    }

    @PostMapping("/unfollow")
    public ResponseEntity<ApiResponse<Object>> unfollow(@RequestBody Follows follows) {
        boolean unfollow = followsService.unfollowUser(follows.getFollowerId(), follows.getFollowingId());
       if(unfollow){
         ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Unfollow thành công!",
            null 
        );
        return ResponseEntity.ok(response);  
       }
       else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "unfollow không thành công!",
            null
        );
        return ResponseEntity.ok(response);
       }

    }
    //Kiểm tra xem đã folow người đó chưa
    @PostMapping("/checkFollow")
    public ResponseEntity<ApiResponse<Object>> Checkfollow(@RequestBody Follows follows) {
        boolean checkfollow = followsService.isFollowing(follows.getFollowerId(), follows.getFollowingId());
       if(checkfollow){
         ApiResponse<Object> response = new ApiResponse<>(
            "success",
            HttpStatus.OK.value(),
            "Đã follow!",
            null 
        );
        return ResponseEntity.ok(response);  
       }
       else{
        ApiResponse<Object> response = new ApiResponse<>(
            "error",
            HttpStatus.NOT_FOUND.value(),
            "Chưa follow!",
            null
        );
        return ResponseEntity.ok(response);
       }

    }

    //Lấy danh sách những người mà mình (flower_id) theo dõi
    @GetMapping("/followingWho/{follower_id}")
    public ResponseEntity<ApiResponse<Object>> isFollowingWho(@PathVariable Integer follower_id){
        List<Follows> userList = followsService.following_Who(follower_id);
        List<Integer> folowings= userList.stream()
           .map(Follows::getFollowingId)
           .collect(Collectors.toList());
        if(!userList.isEmpty()){
            ApiResponse<Object> response = new ApiResponse<>(
                "Success",
                HttpStatus.OK.value(),
                "Lấy danh sách folow thành công",
                folowings
            );
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.BAD_REQUEST.value(),
                "Chưa follow ai?",
                null
            );
            return ResponseEntity.ok(response);  
        }
    }
    
}
