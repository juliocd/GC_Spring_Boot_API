package com.seis739.gourmetcompass.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis739.gourmetcompass.enums.TokenStatus;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.model.UserSession;
import com.seis739.gourmetcompass.repository.UserRepository;
import com.seis739.gourmetcompass.repository.UserSessionReposritory;
import com.seis739.gourmetcompass.utils.Constants;
import com.seis739.gourmetcompass.utils.Helper;

@Service
public class ClerkService implements IClerkService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSessionReposritory userSessionReposritory;

    @Override
    public Optional<UserSession> login(String email, String password) throws NoSuchAlgorithmException {
        UserSession userSession = null;
        String hashPassword = Helper.getMD5Hash(password);
        Optional<User> user =  userRepository.findByEmailAndPassword(email, hashPassword);

        if(!user.isPresent()) {
            return Optional.ofNullable(userSession);
        }

        userSession = new UserSession();
        userSession.setUserId(user.get().getId());
        userSession.setToken(Helper.getMD5Hash(user.get().getEmail() + "_" + Timestamp.valueOf(LocalDateTime.now()).getTime()));
        userSession.setExpires(LocalDateTime.now().plus(Constants.SESSION_TIME_VALUE, Constants.SESSION_TIME_UNIT));

        userSessionReposritory.save(userSession);
        return Optional.ofNullable(userSession);
    }

    @Override
    public Boolean logout(String token) {
        Optional<UserSession> userSession = userSessionReposritory.findByToken(token);

        if(!userSession.isPresent()) {
            return false;
        }

        userSessionReposritory.deleteById(userSession.get().getId());
        return true;
    }

	public TokenStatus getTokenStatus(String token) {
		Optional<UserSession> userSession = userSessionReposritory.findByToken(token);
        
        if(!userSession.isPresent()) {
            return TokenStatus.INVALID;
        }

        LocalDateTime expirDateTime = userSession.get().getExpires();
        if(LocalDateTime.now().isAfter(expirDateTime)) {
            return TokenStatus.EXPIRED;
        }

        return TokenStatus.VALID;
	}

	@Override
	public User getLoggedUser(Map<String, String> headers) throws Exception {
        String token = Helper.getTokenFromHeaders(headers);
        Optional<UserSession> userSession = userSessionReposritory.findByToken(token);

        if(!userSession.isPresent()){
            return null;
        }

        Optional<User> loggedUser = userRepository.findById(userSession.get().getUserId());
        if(!loggedUser.isPresent()) {
            throw new Exception("Logged user not foind");
        }

        return loggedUser.get();
	}
}
