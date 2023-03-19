package com.sidhart.singh.blog.repositories;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.entities.Post;
import com.sidhart.singh.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
//    Derived Query Methods
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
