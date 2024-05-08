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

    @JsonProperty(value="userId")
    @Column(name = "user_id")
    private int userId;

    @JsonProperty(value="name")
    @Column(name = "name")
    private String name;

    @JsonProperty(value="country")
    @Column(name = "country")
    private String country;

    @JsonProperty(value="description")
    @Column(name = "description")
    private String description;

    @JsonProperty(value="ingredients")
    @Column(name = "ingredients")
    private String ingredients;

    @JsonProperty(value="steps")
    @Column(name = "steps")
    private String steps;

    @JsonProperty(value="isPrivate")
    @Column(name = "is_private")
    private Integer isPrivate;

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

        recipeDTO.setId(this.id);
        recipeDTO.setName(this.name);
        recipeDTO.setCountry(this.country != null ? this.country : "");
        recipeDTO.setIsPrivate(this.isPrivate == 1);
        recipeDTO.setDescription(this.description);
        recipeDTO.setIngredients(this.ingredients);
        recipeDTO.setSteps(this.steps);
        recipeDTO.setCreatedAt(this.createdAt);

        return recipeDTO;
	}
}
