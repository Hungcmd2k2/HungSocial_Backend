package com.HungSocial.Server.DTO.Comment;

public class CommentResponse {
    private Integer userId;
    private String  userAvatar;
    private String  userName;
    private Integer postId;
    private String content;
    private Integer parentId;
    public CommentResponse(Integer userId, String userAvatar, String userName, Integer postId, String content,
            Integer parentId) {
        this.userId = userId;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.postId = postId;
        this.content = content;
        this.parentId = parentId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserAvatar() {
        return userAvatar;
    }
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getPostId() {
        return postId;
    }
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
