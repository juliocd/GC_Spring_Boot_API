package com.seis739.gourmetcompass.service;

import java.util.ArrayList;
import java.util.Optional;

import com.seis739.gourmetcompass.dto.RecipeDTO;
import com.seis739.gourmetcompass.model.Recipe;
import com.seis739.gourmetcompass.model.User;

public interface IRecipeService {
    Recipe getRecipe(User user, Integer recipeId) throws Exception;
    Recipe createRecipe(User loggedUser, RecipeDTO recipeDTO) throws Exception;
    Recipe updateRecipe(User loggedUser, Integer recipeId, RecipeDTO recipeDTO) throws Exception;
    Boolean deleteRecipe(User user, Integer recipeId) throws Exception;
    ArrayList<Recipe> getPublicRecipes(Optional<String> query) throws Exception;
}
