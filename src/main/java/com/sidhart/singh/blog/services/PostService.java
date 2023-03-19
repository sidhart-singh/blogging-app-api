package com.sidhart.singh.blog.services;

import com.sidhart.singh.blog.entities.Post;
import com.sidhart.singh.blog.payloads.PostDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    Post updatePost(PostDTO postDTO, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<Post> getPostsByCategory(Integer categoryId);

    List<Post> getPostsByUser(Integer userId);

    List<Post> searchPosts(String keywords);
}
