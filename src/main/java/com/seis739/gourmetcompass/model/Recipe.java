package com.seis739.gourmetcompass.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seis739.gourmetcompass.dto.RecipeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @JsonProperty(value="id")
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1,message = "cannot be empty")
    @JsonProperty(value="userId")
    @Column(name = "user_id")
    private int userId;

    @NotBlank(message = "cannot be empty")
    @JsonProperty(value="name")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "cannot be empty")
    @JsonProperty(value="description")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "cannot be empty")
    @JsonProperty(value="ingredients")
    @Column(name = "ingredients")
    private String ingredients;

    @NotBlank(message = "cannot be empty")
    @JsonProperty(value="steps")
    @Column(name = "steps")
    private String steps;

    @JsonProperty(value="isPrivate")
    @Column(name = "is_private")
    private Integer isPrivate;

    @JsonProperty(value="country")
    @Column(name = "country")
    private String country;

    @JsonProperty(value="createdAt")
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @JsonProperty(value="updatedAt")
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

	public RecipeDTO getPublicRecipe() {
		RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setId(this.getId());
        recipeDTO.setName(this.getName());
        recipeDTO.setCountry(this.getCountry() != null ? this.getCountry() : "");
        recipeDTO.setIsPrivate(this.getIsPrivate() == 1);
        recipeDTO.setDescription(this.getDescription());
        recipeDTO.setIngredients(this.getIngredients());
        recipeDTO.setSteps(this.getSteps());
        recipeDTO.setCreatedAt(this.getCreatedAt());

        return recipeDTO;
	}
}
