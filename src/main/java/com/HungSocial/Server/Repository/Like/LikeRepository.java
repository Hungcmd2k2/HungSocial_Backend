package com.HungSocial.Server.Repository.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HungSocial.Server.Entity.Like.LikeEntity;
import com.HungSocial.Server.Entity.Like.LikePK;



@Repository
public interface  LikeRepository extends JpaRepository<LikeEntity,LikePK> {
    LikeEntity findByUserIdAndPostId(Integer userId, Integer postId);

    @Query(value = "SELECT COUNT(*) FROM likes WHERE post_id = :postId", nativeQuery = true)
    Integer countLikesForPost(Integer postId);
}
