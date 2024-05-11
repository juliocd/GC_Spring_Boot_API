package com.seis739.gourmetcompass.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seis739.gourmetcompass.aspects.RateLimited;
import com.seis739.gourmetcompass.aspects.TokenValidator;
import com.seis739.gourmetcompass.dto.CreateUserDTO;
import com.seis739.gourmetcompass.dto.UserDTO;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.service.ClerkService;
import com.seis739.gourmetcompass.service.UserService;
import com.seis739.gourmetcompass.utils.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(path = "/app/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    ClerkService clerkService;

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping()
    public ApiResponse getUser(@RequestHeader Map<String, String> headers) {

        try {
            User user = clerkService.getLoggedUser(headers);
            Optional<UserDTO> userDTO = userService.getUser(user.getId());
            return new ApiResponse(userDTO.get());
        } catch (Exception exception) {
            return new ApiResponse(exception.getMessage());
        }
    }
    
    @RateLimited
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping()
    public ApiResponse createUser(@Valid @RequestBody CreateUserDTO createUserDTO) 
    {
        try{
            User newUser = userService.createUser(createUserDTO);

            return new ApiResponse(newUser.getPublicUser());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PutMapping()
    public ApiResponse updateUser(@RequestHeader Map<String, String> headers, 
        @Valid @RequestBody UserDTO userDTO) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);
            User newUser = userService.updateUser(user.getId(), userDTO);

            return new ApiResponse(newUser.getPublicUser());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping()
    public ApiResponse deleteUser(@RequestHeader Map<String, String> headers) 
    {
        try{
            User user = clerkService.getLoggedUser(headers);
            userService.deleteUser(user.getId());

            return new ApiResponse(true);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }
}
