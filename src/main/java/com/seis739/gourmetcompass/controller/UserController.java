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
import com.seis739.gourmetcompass.service.UserService;
import com.seis739.gourmetcompass.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(path = "/app/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @RateLimited
    @TokenValidator
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/{userId}")
    public ApiResponse getUser(@RequestHeader Map<String, String> headers, 
        @PathVariable("userId") Integer userId
    ) {

        Optional<UserDTO> userDTO = userService.getUser(userId);

        if(userDTO == null) {
            return new ApiResponse("User not found");
        }

        return new ApiResponse(userDTO.get());
    }
    
    @RateLimited
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping()
    public ApiResponse createUser(@RequestBody CreateUserDTO createUserDTO) 
    {
        try{
            User newUser = userService.createUser(createUserDTO);

            return new ApiResponse(newUser.getPublicUser());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }
}
