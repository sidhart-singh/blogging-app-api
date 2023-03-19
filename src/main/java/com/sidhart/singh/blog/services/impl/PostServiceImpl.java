package com.sidhart.singh.blog.services.impl;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.entities.Post;
import com.sidhart.singh.blog.entities.User;
import com.sidhart.singh.blog.exceptions.ResourceNotFoundException;
import com.sidhart.singh.blog.payloads.PostDTO;
import com.sidhart.singh.blog.repositories.CategoryRepo;
import com.sidhart.singh.blog.repositories.PostRepo;
import com.sidhart.singh.blog.repositories.UserRepo;
import com.sidhart.singh.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.modelMapper.map(postDTO, Post.class);

//        receiving 'title' and 'content' from PostDTO
//        receiving 'userId' and 'categoryId' from URL
//      TODO: create updatePostImage() : to update the default postImage later
        post.setPostImage("default.png");
        post.setPostAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDTO.class);
    }



    @Override
    public Post updatePost(PostDTO postDTO, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keywords) {
        return null;
    }
}
