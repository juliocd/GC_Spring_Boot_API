package com.seis739.gourmetcompass.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis739.gourmetcompass.dto.CreateUserDTO;
import com.seis739.gourmetcompass.dto.UserDTO;
import com.seis739.gourmetcompass.model.User;
import com.seis739.gourmetcompass.repository.UserRepository;
import com.seis739.gourmetcompass.utils.Helper;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

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
            return null;
        }
        
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Integer id) {

        try{
            Optional<User> user = userRepository.findById(id);
            if(!user.isPresent()) {
                return false;
            }

            userRepository.delete(user.get());
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        
        return true;
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        Optional<User> user = userRepository.isUserActiveByEmail(email);

        if(!user.isPresent()) {
            return null;
        }

        return Optional.ofNullable(user.get().getPublicUser());
    }
    
}
