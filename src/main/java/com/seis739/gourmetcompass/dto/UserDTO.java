package com.seis739.gourmetcompass.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email; 

    public UserDTO() { }
}
