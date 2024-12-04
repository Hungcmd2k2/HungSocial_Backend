package com.HungSocial.Server.DTO.Request.Auth;

public class CheckPassword {
    Integer userid;
    String password;

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
