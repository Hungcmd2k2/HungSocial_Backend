package com.HungSocial.Server.Entity.Follows;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "follows")
@IdClass(FollowPK.class)
public class Follows {
     @Id
    private Integer followerId;

    @Id
    private Integer followingId;
    
     public Follows(Integer followerId, Integer followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }
    public Follows() {
    }
    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public Integer getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Integer followingId) {
        this.followingId = followingId;
    }

    
    
}
