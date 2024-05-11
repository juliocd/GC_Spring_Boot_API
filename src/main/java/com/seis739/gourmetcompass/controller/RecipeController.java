package com.seis739.gourmetcompass.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seis739.gourmetcompass.aspects.RateLimited;
import com.seis739.gourmetcompass.aspects.TokenValidator;
import com.seis739.gourmetcompass.dto.RecipeDTO;
import com.seis739.gourmetcompass.model.Recipe;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.service.ClerkService;
import com.seis739.gourmetcompass.service.RecipeService;
import com.seis739.gourmetcompass.utils.ApiResponse;

@RestController
@RequestMapping(path = "/app/recipe")
public class RecipeController {
    
    @Autowired
    RecipeService recipeService;

    @Autowired
    ClerkService clerkService;

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/{recipeId}")
    public ApiResponse getRecipe(@RequestHeader Map<String, String> headers, 
        @PathVariable("recipeId") Integer recipeId) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);
            Recipe newRecipe = recipeService.getRecipe(user, recipeId);

            return new ApiResponse(newRecipe.getPublicRecipe());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping()
    public ApiResponse createRecipe(@RequestHeader Map<String, String> headers, 
        @RequestBody RecipeDTO recipeDTO) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);
            Recipe newRecipe = recipeService.createRecipe(user, recipeDTO);

            return new ApiResponse(newRecipe.getPublicRecipe());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PutMapping("/{recipeId}")
    public ApiResponse updateRecipe(@RequestHeader Map<String, String> headers, 
        @RequestBody RecipeDTO recipeDTO, @PathVariable("recipeId") Integer recipeId) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);
            Recipe recipe = recipeService.updateRecipe(user, recipeId, recipeDTO);

            return new ApiResponse(recipe.getPublicRecipe());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping("/{recipeId}")
    public ApiResponse deleteRecipe(@RequestHeader Map<String, String> headers, 
        @PathVariable("recipeId") Integer recipeId) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);

            return new ApiResponse(recipeService.deleteRecipe(user, recipeId));
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/public")
    public ApiResponse listRecipes(@RequestParam Optional<String> query) 
    {
        try{
            ArrayList<Recipe> publicRecipes = recipeService.listPublicRecipes(query);

            ArrayList<RecipeDTO> publicRecipesDTO = new ArrayList<RecipeDTO>();
            for (Recipe publicRecipe : publicRecipes) {
                publicRecipesDTO.add(publicRecipe.getPublicRecipe());
            }

            return new ApiResponse(publicRecipesDTO);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/list")
    public ApiResponse listPublicRecipes(@RequestHeader Map<String, String> headers, 
        @RequestParam Optional<String> query) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);
            ArrayList<Recipe> publicRecipes = recipeService.listRecipes(user, query);

            ArrayList<RecipeDTO> publicRecipesDTO = new ArrayList<RecipeDTO>();
            for (Recipe publicRecipe : publicRecipes) {
                publicRecipesDTO.add(publicRecipe.getPublicRecipe());
            }

            return new ApiResponse(publicRecipesDTO);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }
}
