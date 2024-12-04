package com.HungSocial.Server.Service.Like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.Like.LikeEntity;
import com.HungSocial.Server.Repository.Like.LikeRepository;

@Service
public class LikeService {
   
    @Autowired
    private LikeRepository likeRepository;

    public LikeEntity Like(Integer userId,Integer postId){
        LikeEntity likeEntity = new LikeEntity(userId,postId);
        return likeRepository.save(likeEntity);
    }

    public boolean unLike(Integer userId,Integer postId){
        LikeEntity likeEntity = likeRepository.findByUserIdAndPostId(userId, postId);
        if(likeEntity!=null){
            likeRepository.delete(likeEntity);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLiked(Integer userId,Integer postId){
        LikeEntity likeEntity = likeRepository.findByUserIdAndPostId(userId, postId);
        if(likeEntity!=null){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Đếm số lượt thích cho bài viết

    public Integer totalLike(Integer postId){
        return likeRepository.countLikesForPost(postId);
    }
}
