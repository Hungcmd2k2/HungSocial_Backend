package com.HungSocial.Server.exception;

public enum ErrorCode {
    TOKEN_FALSE(9999,"Token sai"),
    TOKEN_EXISTED(1001,"Token existed."),
    USER_NOT_EXISTED(1005,"User not existed."),
    EMAIL_EXISTED(1002,"Email existed."),
    USERNAME_INVALID(1003,"Username must be least 8 characters"),
    UNAUTHENTICATED(1006,"Unauthenticated!");

    ErrorCode(int code,String message){
        this.code=code;
        this.message=message;
    }
   private int code;
   private String message;

    public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

