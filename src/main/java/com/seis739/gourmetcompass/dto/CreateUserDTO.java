package com.seis739.gourmetcompass.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonSerialize
public class CreateUserDTO extends UserDTO {

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, message = "Password should have at least 2 characters")
    private String password; 

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
