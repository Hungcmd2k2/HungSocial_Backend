package com.HungSocial.Server.Entity.Follows;

import java.io.Serializable;
import java.util.Objects;

public class FollowPK implements Serializable {
    private Integer followerId;
    private Integer followingId;

   
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowPK followPK = (FollowPK) o;
        return Objects.equals(followerId, followPK.followerId) && 
               Objects.equals(followingId, followPK.followingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followingId);
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
