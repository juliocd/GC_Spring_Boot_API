package com.seis739.gourmetcompass.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class UserDTO {
    private int id;

    @Pattern(regexp="^[A-Za-z]*$",message = "must be an string")
    private String firstName;

    @Pattern(regexp="^[A-Za-z]*$",message = "must be an string")
    private String lastName;

    @Email(message = "invalid email format", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email; 

    public UserDTO() { }
}

