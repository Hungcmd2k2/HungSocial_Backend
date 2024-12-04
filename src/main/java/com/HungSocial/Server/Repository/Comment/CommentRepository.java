package com.HungSocial.Server.Repository.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.Comment.CommentEntity;


@Repository
public interface  CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByPostId(Integer postId);

    @Query(value = "SELECT COUNT(*) FROM comments WHERE post_id = :postId", nativeQuery = true)
    Integer countCommentsForPost(Integer postId);
}
