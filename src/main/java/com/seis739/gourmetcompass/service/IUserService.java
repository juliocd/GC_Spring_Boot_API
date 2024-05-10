package com.seis739.gourmetcompass.service;

import java.util.Optional;

import com.seis739.gourmetcompass.dto.CreateUserDTO;
import com.seis739.gourmetcompass.dto.UserDTO;
import com.seis739.gourmetcompass.model.User;

public interface IUserService {
    public Optional<UserDTO> getUser(Integer id);
    public User createUser(CreateUserDTO createUserDTO) throws Exception;
    public User updateUser(Integer userId, UserDTO createUserDTO) throws Exception;
    public void deleteUser(Integer id)  throws Exception;
    public Optional<UserDTO> getUserByEmail(String email);
}
