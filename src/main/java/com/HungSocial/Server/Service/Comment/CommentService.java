package com.HungSocial.Server.Service.Comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.DTO.Comment.CommentRequest;
import com.HungSocial.Server.Entity.Comment.CommentEntity;
import com.HungSocial.Server.Repository.Comment.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity addComment(CommentRequest cm) {
        Integer userId = cm.getUserId();
        Integer postId = cm.getPostId();
        String content = cm.getContent();
        Integer parentId = cm.getParentId();
        LocalDateTime time = LocalDateTime.now().withSecond(0).withNano(0);
        CommentEntity comment = new CommentEntity(userId, postId, content, time, parentId);
        commentRepository.save(comment);
        return comment;
    }

    // Lấy hết commnent của post

    public List<CommentEntity> getAllComment(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    // Lấy số lượng comment cho post

    public Integer countComment(Integer postId) {
        return commentRepository.countCommentsForPost(postId);
    }
}
