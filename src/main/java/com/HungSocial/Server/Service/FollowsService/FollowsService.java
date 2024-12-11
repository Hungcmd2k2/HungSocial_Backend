package com.HungSocial.Server.Service.FollowsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.Follows.Follows;
import com.HungSocial.Server.Repository.FollowsRepository.FollowsRepository;

@Service
public class FollowsService {
    @Autowired
    private FollowsRepository followsRepository;

    public Follows followUser(Integer followerId, Integer followingId) {
        Follows follow = new Follows(followerId, followingId);
        return  followsRepository.save(follow);  
    }

    public boolean  unfollowUser(Integer followerId, Integer followingId) {
        Follows follow = followsRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (follow != null) {
            followsRepository.delete(follow);
            return true;
        }
        else{
            return false;
        }
    }

    //Kiểm tra xem có follow người đó chưaa
    public boolean isFollowing(Integer followerId, Integer followingId) {
        return followsRepository.existsFollow(followerId, followingId).isPresent();
    }
    //Lấy danh sách mà userid đang follow
    public  List<Follows> following_Who(Integer follower_id){
        return followsRepository.findFollowingByFollowerId(follower_id);
    }
    //lấy danh sách follower
    public  List<Follows> followers(Integer following_id){
        return followsRepository.findFollowingByFollowingId(following_id);
    }
}
