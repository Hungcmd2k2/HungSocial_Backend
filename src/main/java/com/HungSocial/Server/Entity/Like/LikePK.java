package com.HungSocial.Server.Entity.Like;

import java.io.Serializable;
import java.util.Objects;



public class LikePK implements Serializable {
    private Integer userId;
    private Integer postId;

       @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikePK LikePK = (LikePK) o;
        return Objects.equals(userId, LikePK.userId) && 
               Objects.equals(postId, LikePK.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
