package com.sidhart.singh.blog.services.impl;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.entities.Post;
import com.sidhart.singh.blog.entities.User;
import com.sidhart.singh.blog.exceptions.ResourceNotFoundException;
import com.sidhart.singh.blog.payloads.PostDTO;
import com.sidhart.singh.blog.payloads.PostResponse;
import com.sidhart.singh.blog.repositories.CategoryRepo;
import com.sidhart.singh.blog.repositories.PostRepo;
import com.sidhart.singh.blog.repositories.UserRepo;
import com.sidhart.singh.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {

        Post updatedPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        updatedPost.setPostTitle(postDTO.getPostTitle());
        updatedPost.setPostContent(postDTO.getPostContent());

        this.postRepo.save(updatedPost);

        return this.modelMapper.map(updatedPost, PostDTO.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

//        prepare Sort object for pagination
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort = Sort.by(sortBy).ascending();
        else
            sort = Sort.by(sortBy).descending();

//        Pagination:
//        1. create a object Pageable object passing pageNumber and pageSize and sortby
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

//        2. get Page<> object from that pageable object
        Page<Post> postPage = this.postRepo.findAll(pageable);

//        3. get the list of post from the Page<> object
        List<Post> postPageList = postPage.getContent();

        List<PostDTO> postDTOList = postPageList.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

//        create a custom PageResponse object :
//        to return additional information
        PostResponse postResponse = new PostResponse(postDTOList,
                                postPage.getNumber(),
                                postPage.getSize(),
                                postPage.getTotalElements(),
                                postPage.getTotalPages(),
                                postPage.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        PostDTO postDTO = this.modelMapper.map(post, PostDTO.class);

        return postDTO;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> postList = this.postRepo.findByCategory(category);

        List<PostDTO> postDTOList = postList.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> postList = this.postRepo.findByUser(user);

        List<PostDTO> postDTOList = postList.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOList;
    }

    @Override
    public List<PostDTO> searchPosts(String keywords) {
        return null;
    }
}
