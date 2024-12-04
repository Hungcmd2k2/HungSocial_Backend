package com.HungSocial.Server.DTO.Post;

import java.util.List;

public class DataPostDTO<T>{
    private String userAvatar;
    private String userName;
    
    private List<T> listPost;

    

    public List<T> getListPost() {
        return listPost;
    }

    public void setListPost(List<T> listPost) {
        this.listPost = listPost;
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

   
    
}
