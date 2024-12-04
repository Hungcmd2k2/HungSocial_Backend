package com.HungSocial.Server.DTO.User;

import java.time.LocalDate;

public class UserOtherInfor {
    private Integer userid;
    private String username;
    private String fullname;
    private String email;
    private String avatar;
    private String coverphoto;
    private LocalDate dob;
    private String linksocial;
    private String lovesong;
    private String education;
    private String address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

   
}
