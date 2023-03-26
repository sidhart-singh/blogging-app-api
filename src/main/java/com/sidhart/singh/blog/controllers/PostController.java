package com.sidhart.singh.blog.controllers;

import com.sidhart.singh.blog.payloads.ApiResponse;
import com.sidhart.singh.blog.payloads.PostDTO;
import com.sidhart.singh.blog.payloads.PostResponse;
import com.sidhart.singh.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping("/users/{userId}/categories/{categoryId}/posts/")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDTO newPost = this.postService.createPost(postDTO, userId, categoryId);

        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

//    get list of posts of a user
    @GetMapping("/users/{userId}/posts/")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
        List<PostDTO> postDTOList = this.postService.getPostsByUser(userId);

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/posts/")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDTO> postDTOList = this.postService.getPostsByCategory(categoryId);

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

//    parameters passed as params :
//    initial indexing : pageNumber - 0
//    values : sortDir - asc & desc
//    in Postman - params : key : value
//    not specifying url
    @GetMapping("/posts/")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        PostDTO postDTO = this.postService.getPostById(postId);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted.", true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId){
        PostDTO updatedPost = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords){
        List<PostDTO> postDTOListByTitle = this.postService.searchPosts(keywords);

        return new ResponseEntity<>(postDTOListByTitle, HttpStatus.OK);
    }

}
