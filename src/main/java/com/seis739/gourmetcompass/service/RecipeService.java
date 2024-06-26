package com.seis739.gourmetcompass.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis739.gourmetcompass.dto.RecipeDTO;
import com.seis739.gourmetcompass.model.Recipe;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.repository.RecipeRepository;
import com.seis739.gourmetcompass.utils.Helper;

@Service
public class RecipeService implements IRecipeService{

    @Autowired
    RecipeRepository recipeRepository;

	@Override
	public Recipe createRecipe(User loggedUser, RecipeDTO recipeDTO) throws Exception {

        try {
            Recipe recipe = new Recipe();

            recipe.setName(recipeDTO.getName());
            recipe.setUserId(loggedUser.getId());
            recipe.setDescription(recipeDTO.getDescription());
            recipe.setIngredients(recipeDTO.getIngredients());
            recipe.setSteps(recipeDTO.getSteps());
            recipe.setCreatedAt(LocalDateTime.now());

            recipe.setCountry(recipeDTO.getCountry() != null ? recipeDTO.getCountry() : "");
            recipe.setIsPrivate(recipeDTO.getIsPrivate() == false ? 0 : 1);
            recipe.setCreatedAt(LocalDateTime.now());

            return recipeRepository.save(recipe);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception(exception.getMessage());
        }
	}

    public Recipe updateRecipe(User loggedUser, Integer recipeId, RecipeDTO recipeDTO) throws Exception {
        if (recipeId < 1) {
            throw new Exception("Recipe id is required.");
        }

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(!recipe.isPresent()) {
            throw new Exception("Recipe not found.");
        }

        try {
            if(recipeDTO.getName() != null) {
                recipe.get().setName(recipeDTO.getName());
            }

            if(recipeDTO.getDescription() != null) {
                recipe.get().setDescription(recipeDTO.getDescription());
            }

            if(recipeDTO.getIngredients() != null) {
                recipe.get().setIngredients(recipeDTO.getIngredients());
            }

            if(recipeDTO.getSteps() != null) {
                recipe.get().setSteps(recipeDTO.getSteps());
            }

            if(recipeDTO.getCountry() != null) {
                recipe.get().setCountry(recipeDTO.getCountry());
            }

            if(recipeDTO.getIsPrivate() != null) {
                recipe.get().setIsPrivate(recipeDTO.getIsPrivate() ? 1 : 0);
            }

            recipe.get().setUpdatedAt(LocalDateTime.now());

            return recipeRepository.save(recipe.get());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception(exception.getMessage());
        }
    }

    public Recipe getRecipe(User user, Integer recipeId) throws Exception {
        if (recipeId < 1) {
            throw new Exception("Recipe id is required.");
        }

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(!recipe.isPresent()) {
            throw new Exception("Recipe not found.");
        }

        return recipe.get();
    }

    @Override
    public Boolean deleteRecipe(User user, Integer recipeId) throws Exception {
        if (recipeId < 1) {
            throw new Exception("Recipe id is required.");
        }

        Boolean recipe = recipeRepository.existsById(recipeId);
        if(!recipe) {
            throw new Exception("Recipe not found.");
        }

        try {
            recipeRepository.deleteById(recipeId);
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error deleting recipe.");
        }
    }

    @Override
    public ArrayList<Recipe> getPublicRecipes(Optional<String> query) throws Exception {
        try {
            if(query.isPresent()) {
                return recipeRepository.filterPublicByQuery(Helper.formatForSearching(query.get()));
            }

            return recipeRepository.findAllPublic();
        } catch(Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error getting public recipes.");
        }
    }
}
