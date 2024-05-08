package com.seis739.gourmetcompass.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class RecipeDTO {
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String steps;
    private String country;
    private Boolean isPrivate;
    private LocalDateTime createdAt;
}
