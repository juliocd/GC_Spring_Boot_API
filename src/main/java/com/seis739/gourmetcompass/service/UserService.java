package com.seis739.gourmetcompass.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis739.gourmetcompass.dto.CreateUserDTO;
import com.seis739.gourmetcompass.dto.UserDTO;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.repository.UserRepository;
import com.seis739.gourmetcompass.repository.UserSessionRepository;
import com.seis739.gourmetcompass.utils.Helper;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    public Optional<UserDTO> getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            return null;
        }

        return Optional.ofNullable(user.get().getPublicUser());
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) throws Exception {
        
        if(this.getUserByEmail(createUserDTO.getEmail()) != null) {
            throw new Exception("Email is already associated to another account.");
        }
        
        User user = new User();
        try {
            user.setFirstName(createUserDTO.getFirstName());
            user.setLastName(createUserDTO.getLastName());
            user.setEmail(createUserDTO.getEmail());
            user.setPassword(Helper.getMD5Hash(createUserDTO.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error creating an user.");
        }
        
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) throws Exception {

        try{
            Optional<User> user = userRepository.findById(id);
            if(!user.isPresent()) {
                throw new Exception("User not found.");
            }

            userSessionRepository.deleteByUserId(user.get().getId());

            userRepository.delete(user.get());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error deleting user.");
        }
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        Optional<User> user = userRepository.isUserActiveByEmail(email);

        if(!user.isPresent()) {
            return null;
        }

        return Optional.ofNullable(user.get().getPublicUser());
    }

    @Override
    public User updateUser(Integer userId, UserDTO userDTO) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new Exception("User not found.");
        }
        
        try {
            if(userDTO.getFirstName() != null) {
                user.get().setFirstName(userDTO.getFirstName());
            }

            if(userDTO.getLastName() != null) {
                user.get().setLastName(userDTO.getLastName());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        
        return userRepository.save(user.get());
    }
    
}
