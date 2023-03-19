package com.sidhart.singh.blog.controllers;

import com.sidhart.singh.blog.entities.User;
import com.sidhart.singh.blog.payloads.ApiResponse;
import com.sidhart.singh.blog.payloads.UserDTO;
import com.sidhart.singh.blog.services.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Valid Annotation :
//    Used with parameters to be validated &
//    methods which require validate received data : create and update

    @Autowired
    private UserService userService;

//    POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
        UserDTO newUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

//    PUT -  update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Integer userId){
        UserDTO user = this.userService.getUserById(userId);

        UserDTO updatedUser = this.userService.updateUser(userDto, userId);

        return ResponseEntity.ok(updatedUser);
    }

//    DELETE - delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);

        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

//    GET -
//    Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

//    Get User by userId
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }

    /*
    This:
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    is equivalent to this:
        return ResponseEntity.ok(this.userService.getUserById(userId));
     */

}
