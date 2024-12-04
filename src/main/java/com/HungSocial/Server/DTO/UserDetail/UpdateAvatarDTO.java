package com.HungSocial.Server.DTO.UserDetail;

public class UpdateAvatarDTO {
    private Integer userid;
    public UpdateAvatarDTO(Integer userid, String filepath) {
        this.userid = userid;
        this.filepath = filepath;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    private String filepath;


    public UpdateAvatarDTO(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
