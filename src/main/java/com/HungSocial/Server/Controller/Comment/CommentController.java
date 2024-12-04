package com.HungSocial.Server.Controller.Comment;

import java.util.List;
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

import com.HungSocial.Server.DTO.Comment.CommentRequest;
import com.HungSocial.Server.DTO.Comment.CommentResponse;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Entity.Comment.CommentEntity;
import com.HungSocial.Server.Entity.User.User;
import com.HungSocial.Server.Entity.UserDetails.UserDetails;
import com.HungSocial.Server.Service.Comment.CommentService;
import com.HungSocial.Server.Service.User.UserService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> addComment(@RequestBody CommentRequest cm) {
        CommentEntity comment = commentService.addComment(cm);
        if (comment != null) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Bình luận bài viết thành công!",
                    comment);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "bình luận bài viết không thành công!",
                    null);
            return ResponseEntity.ok(response);
        }
    }

    // Get all comment
    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<Object>> getAllComment(@PathVariable Integer postId) {
        List<CommentEntity> comments = commentService.getAllComment(postId);

        // Tạo danh sách CommentResponse
        List<CommentResponse> commentResponses = comments.stream()
                .map(comment -> {
                    // Lấy thông tin user
                    Optional<User> userOpt = userService.getUserById(comment.getUserId());
                    String userName = userOpt.map(User::getUsername).orElse("Unknown");

                    // Lấy thông tin userDetails
                    Optional<UserDetails> userDetailsOpt = userService.getUserDetail(comment.getUserId());
                    String userAvatar = userDetailsOpt.map(UserDetails::getAvatar).orElse("default-avatar.png");

                    // Chuyển đổi sang CommentResponse
                    return new CommentResponse(
                            comment.getUserId(),
                            userAvatar,
                            userName,
                            comment.getPostId(),
                            comment.getContent(),
                            comment.getParentId() // Nếu có trường `parentId`, thêm vào đây
                    );
                })
                .collect(Collectors.toList());

        // Kiểm tra và trả về API response
        if (!commentResponses.isEmpty()) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Lấy hết comment thành công!",
                    commentResponses);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "Không lấy được comment thành công!",
                    commentResponses);
            return ResponseEntity.ok(response);
        }
    }

    // Đếm lượt thích
    @GetMapping("/total/{postId}")
    public ResponseEntity<ApiResponse<Object>> totalLike(@PathVariable Integer postId) {
        Integer count = commentService.countComment(postId);
        ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Tổng số lượt comment là ",
                count);
        return ResponseEntity.ok(response);
    }

}
