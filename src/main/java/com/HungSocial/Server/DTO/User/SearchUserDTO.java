package com.HungSocial.Server.DTO.User;

public class SearchUserDTO {
    private Integer userid;
    private String username;
    private String fullname;
    private String avatar;

    public SearchUserDTO(Integer userid,String username,String fullname,String avatar){
      this.userid= userid;
      this.username=username;
      this.fullname=fullname;
      this.avatar=avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avartar) {
        this.avatar = avartar;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}
