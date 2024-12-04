package com.HungSocial.Server.Controller.Like;

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
import com.HungSocial.Server.Entity.Like.LikeEntity;
import com.HungSocial.Server.Service.Like.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createLike(@RequestBody LikeEntity likeRequest) {
        LikeEntity likeEntity = likeService.Like(likeRequest.getUserId(), likeRequest.getPostId());
        if (likeEntity != null) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Like bài viết thành công!",
                    likeEntity);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "Like bài viết không thành công!",
                    null);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<Object>> deleteLike(@RequestBody LikeEntity likeRequest) {
        boolean deleteLike = likeService.unLike(likeRequest.getUserId(), likeRequest.getPostId());
        if (deleteLike) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "UnLike bài viết thành công!",
                    null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "UNLike bài viết không thành công!",
                    null);
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping("/isLiked")
    public ResponseEntity<ApiResponse<Object>> checkLike(@RequestBody LikeEntity likeRequest) {
        boolean deleteLike = likeService.isLiked(likeRequest.getUserId(), likeRequest.getPostId());
        if (deleteLike) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Đã like bài viết ",
                    null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.NOT_FOUND.value(),
                    "Chưa like bài viết",
                    null);
            return ResponseEntity.ok(response);
        }

    }

    // Đếm lượt thích
    @GetMapping("/total/{postId}")
    public ResponseEntity<ApiResponse<Object>> totalLike(@PathVariable Integer postId) {
        Integer count = likeService.totalLike(postId);
        ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Tổng số lượt like là ",
                count);
        return ResponseEntity.ok(response);
    }

}
