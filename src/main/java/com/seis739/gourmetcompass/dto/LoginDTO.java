package com.seis739.gourmetcompass.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class LoginDTO {

    @NotBlank(message = "cannot be empty")
    @Email(message = "invalid email format", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "cannot be empty")
    private String password;

    public LoginDTO() { }
}
