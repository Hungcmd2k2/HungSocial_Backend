package com.HungSocial.Server.DTO.UserDetail;

import java.time.LocalDate;

public class UserDetailDTO {

    private Integer userid;
    private String fullname;
    private LocalDate dob;
    private String linksocial;
    private String lovesong;
    private String education;
    private String address;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
}
