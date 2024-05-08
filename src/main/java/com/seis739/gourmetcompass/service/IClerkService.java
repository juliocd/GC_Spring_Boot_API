package com.seis739.gourmetcompass.service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import com.seis739.gourmetcompass.enums.TokenStatus;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.model.UserSession;

public interface IClerkService {
    Optional<UserSession> login(String email, String password) throws NoSuchAlgorithmException;
    Boolean logout(String token);
    TokenStatus getTokenStatus(String token);
    User getLoggedUser(Map<String, String> headers) throws Exception;
}
