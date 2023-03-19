package com.sidhart.singh.blog.payloads;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.entities.User;
import com.sidhart.singh.blog.repositories.UserRepo;
import lombok.Data;

public class PostDTO {
    private String postTitle;
    private String postContent;
    private Data postAddedDate;
    private Category category;
    private User user;
}
