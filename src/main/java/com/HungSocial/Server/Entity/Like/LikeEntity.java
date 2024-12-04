package com.HungSocial.Server.Entity.Like;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="likes")
@IdClass(LikePK.class)
public class LikeEntity {
     @Id
    private Integer userId;
    @Id
    private Integer postId;
    public LikeEntity(Integer userId, Integer postId) {
        this.userId = userId;
        this.postId = postId;
    }
    public LikeEntity(){}
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

}
