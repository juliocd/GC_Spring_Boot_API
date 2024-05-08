package com.seis739.gourmetcompass.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CreateUserDTO extends UserDTO {
    private String password; 

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
