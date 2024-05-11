package com.seis739.gourmetcompass.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class RecipeDTO {
    private int id;

    @Pattern(regexp="^[A-Za-z].*$",message = "must be an string")
    private String name;

    @Pattern(regexp="^[A-Za-z].*$",message = "must be an string")
    private String description;

    @Pattern(regexp="^[A-Za-z].*$",message = "must be an string")
    private String ingredients;

    @Pattern(regexp="^[A-Za-z].*$",message = "must be an string")
    private String steps;

    @Pattern(regexp="^[A-Za-z]*$",message = "must be an string")
    private String country;

    private Boolean isPrivate;

    private LocalDateTime createdAt;
}
