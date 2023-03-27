package com.sidhart.singh.blog.controllers;

import com.sidhart.singh.blog.config.AppConstans;
import com.sidhart.singh.blog.payloads.ApiResponse;
import com.sidhart.singh.blog.payloads.PostDTO;
import com.sidhart.singh.blog.payloads.PostResponse;
import com.sidhart.singh.blog.services.FileService;
import com.sidhart.singh.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstans.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstans.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstans.SORT_DIR, required = false) String sortDir){
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

//    upload post image
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable Integer postId) throws IOException {
//        if provided postId is not found : exception handled here : not going further : not throwing IOException
        PostDTO postDTO = this.postService.getPostById(postId);

//        TODO: handle with GlobalExceptionHandler
        String fileName = this.fileService.uploadImage(path, image);
        postDTO.setPostImage(fileName);
        PostDTO updatedPostDTO = this.postService.updatePost(postDTO, postId);

        return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
    }

//    serve post image
    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadPostImage(@PathVariable("imageName") String imageName,
                                  HttpServletResponse response) throws IOException {
        InputStream inputStream = this.fileService.getResource(path, imageName);

//        set response type to media-image
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(inputStream, response.getOutputStream());
    }

}
