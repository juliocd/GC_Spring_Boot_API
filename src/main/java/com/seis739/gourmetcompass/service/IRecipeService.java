package com.seis739.gourmetcompass.service;

import java.util.ArrayList;
import java.util.Optional;

import com.seis739.gourmetcompass.dto.RecipeDTO;
import com.seis739.gourmetcompass.model.Recipe;
import com.seis739.gourmetcompass.model.User;

public interface IRecipeService {
    Recipe getRecipe(User user, Integer recipeId) throws Exception;
    Recipe createRecipe(User user, RecipeDTO recipeDTO) throws Exception;
    Recipe updateRecipe(User user, Integer recipeId, RecipeDTO recipeDTO) throws Exception;
    Boolean deleteRecipe(User user, Integer recipeId) throws Exception;
    ArrayList<Recipe> listPublicRecipes(Optional<String> query) throws Exception;
    ArrayList<Recipe> listRecipes(User user, Optional<String> query) throws Exception;
}
