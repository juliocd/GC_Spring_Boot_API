package com.seis739.gourmetcompass.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserSessionDTO {
    private Integer userId;
    private String token;
    private LocalDateTime expires;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public LocalDateTime getExpires() {
        return expires;
    }
    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
