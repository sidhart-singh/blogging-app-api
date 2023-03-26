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

//    custom method for search service :
//    generates a query with 'LIKE' clause with given field :
//    return list of posts with their title containing the required keywords
    List<Post> findByPostTitleContaining(String keywords);
}
