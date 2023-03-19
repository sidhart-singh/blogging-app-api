package com.sidhart.singh.blog.repositories;

import com.sidhart.singh.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
