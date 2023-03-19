package com.sidhart.singh.blog.services.impl;

import com.sidhart.singh.blog.entities.User;
import com.sidhart.singh.blog.exceptions.ResourceNotFoundException;
import com.sidhart.singh.blog.payloads.UserDTO;
import com.sidhart.singh.blog.repositories.UserRepo;
import com.sidhart.singh.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSeverImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
//        Convert the userDTO to user entity
        User user = this.dtoToUser(userDto);

//        Save the user to the repository
        User savedUser = this.userRepo.save(user);

        return this.userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User", "userId", userId
        ));

//        Update the user with the new userDto
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

//        Save the updated user the repo:
//        returning the updatedUser but repository was not updated
        this.userRepo.save(user);

//        Return the userDto from the updated user
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();

        List<UserDTO> userDtoList = users.stream().map(user -> this.modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }

    public UserDTO userToDTO(User user){
        return this.modelMapper.map(user, UserDTO.class);
    }
}
