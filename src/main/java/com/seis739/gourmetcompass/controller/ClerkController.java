package com.seis739.gourmetcompass.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seis739.gourmetcompass.aspects.RateLimited;
import com.seis739.gourmetcompass.dto.LoginDTO;
import com.seis739.gourmetcompass.model.UserSession;
import com.seis739.gourmetcompass.service.ClerkService;
import com.seis739.gourmetcompass.utils.ApiResponse;

@RestController
@RequestMapping(path = "/app/clerk")
public class ClerkController {
    
    @Autowired
    private ClerkService clerkService;

    @RateLimited
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDTO loginDTO) {
        try{
            Optional<UserSession> newUserSession = clerkService.login(loginDTO.getEmail(), loginDTO.getPassword());

            if(!newUserSession.isPresent()) {
                return new ApiResponse("Invalid credentials");
            }

            return new ApiResponse(newUserSession.get().getPublicFormat());
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }

    @RateLimited
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/logout")
    public ApiResponse logout(@RequestParam String token) {
        try{
            Boolean result = clerkService.logout(token);

            if(!result) {
                return new ApiResponse("Invalid token");
            }

            return new ApiResponse(result);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage());
        }
    }
}
