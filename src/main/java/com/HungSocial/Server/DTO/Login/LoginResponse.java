package com.HungSocial.Server.DTO.Login;

public class LoginResponse {
    private int userid;
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    private String email;
    private String token;


    public LoginResponse(int userid,String username,String email, String token) {
        this.userid = userid;
        this.username=username;
        this.email = email;
        this.token = token;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
