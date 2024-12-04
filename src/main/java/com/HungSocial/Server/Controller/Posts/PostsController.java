package com.HungSocial.Server.Controller.Posts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.HungSocial.Server.Entity.File.FileEntity;
import com.HungSocial.Server.Entity.Posts.Post;
import com.HungSocial.Server.Entity.User.User;
import com.HungSocial.Server.Entity.UserDetails.UserDetails;
import com.HungSocial.Server.Service.File.FileService;
import com.HungSocial.Server.Service.Posts.PostService;
import com.HungSocial.Server.Service.User.UserService;

@RestController
@RequestMapping("/api/post")
public class PostsController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createPost(@RequestBody Post postRequest) {
        Post newPost = postService.createPost(postRequest);
        if (newPost != null) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Tạo bài viết thành công!",
                    newPost);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "Tạo bài viết không thành công!",
                    null);
            return ResponseEntity.ok(response);
        }

    }

    @GetMapping("/{Userid}")
    public ResponseEntity<ApiResponse<Object>> GetListPost(@PathVariable Integer Userid) {
        List<Post> listPost = postService.getPostbyUserid(Userid);
        Optional<User> userInfo = userService.getUserById(Userid);
        Optional<UserDetails> userDetails = userService.getUserDetail(Userid);

        if (!listPost.isEmpty() && userInfo.isPresent() && userDetails.isPresent()) {
            String userAvatar = userDetails.get().getAvatar();
            String userName = userInfo.get().getUsername();

            // Thêm userAvatar và userName vào từng bài viết
            List<Map<String, Object>> enrichedPosts = listPost.stream()
                    .map(post -> {
                        List<FileEntity> fileEntities = fileService.getAllFile(post.getId());
                        List<String> imagePaths = fileEntities.stream()
                                .map(FileEntity::getFilePath) // Lấy đường dẫn file
                                .collect(Collectors.toList()); // Thu thập vào danh sách

                        Map<String, Object> postWithUserDetails = new HashMap<>();
                        postWithUserDetails.put("id", post.getId());
                        postWithUserDetails.put("userId", post.getUserId());
                        postWithUserDetails.put("content", post.getContent());
                        postWithUserDetails.put("privacy", post.getPrivacy());
                        postWithUserDetails.put("tags", post.getTags());
                        postWithUserDetails.put("createdAt", post.getCreatedAt());
                        postWithUserDetails.put("updatedAt", post.getUpdatedAt());
                        postWithUserDetails.put("userAvatar", userAvatar);
                        postWithUserDetails.put("userName", userName);
                        postWithUserDetails.put("images", imagePaths); // Thêm danh sách đường dẫn ảnh
                        return postWithUserDetails;
                    })
                    .collect(Collectors.toList());

            ApiResponse<Object> response = new ApiResponse<>(
                    "Success",
                    HttpStatus.OK.value(),
                    "Lấy bài viết thành công!",
                    enrichedPosts);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "Lấy bài viết không thành công!",
                    null);
            return ResponseEntity.ok(response);
        }
    }

}
