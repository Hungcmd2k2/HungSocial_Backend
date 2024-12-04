package com.HungSocial.Server.DTO.UserDetail;

import java.time.LocalDate;



public class UserListChat {
    private Integer id;
    private Integer userid;
    private String username;
    private String fullname;
    private String avatar;
    private String coverphoto;
    private LocalDate dob;
    private String linksocial;
    private String lovesong;
    private String education;
    private String address;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public UserListChat(Integer id, Integer userid, String username, String fullname, String avatar, String coverphoto,
            LocalDate dob, String linksocial, String lovesong, String education, String address) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.fullname = fullname;
        this.avatar = avatar;
        this.coverphoto = coverphoto;
        this.dob = dob;
        this.linksocial = linksocial;
        this.lovesong = lovesong;
        this.education = education;
        this.address=address;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
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
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getCoverphoto() {
        return coverphoto;
    }
    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getLinksocial() {
        return linksocial;
    }
    public void setLinksocial(String linksocial) {
        this.linksocial = linksocial;
    }
    public String getLovesong() {
        return lovesong;
    }
    public void setLovesong(String lovesong) {
        this.lovesong = lovesong;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

}
