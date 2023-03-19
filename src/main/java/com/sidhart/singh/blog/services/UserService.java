package com.sidhart.singh.blog.services;

import com.sidhart.singh.blog.entities.User;
import com.sidhart.singh.blog.payloads.UserDTO;
import jakarta.transaction.UserTransaction;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUsers();

    void deleteUser(Integer user_id);
}
