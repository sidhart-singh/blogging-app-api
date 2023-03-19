package com.sidhart.singh.blog.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "title", length = 10, nullable = false)
    private String postTitle;
    @Column(name = "content", length = 1000)
    private String postContent;
    @Column(name = "image")
    private String postImage;
    @Column(name = "addedDate", nullable = false)
    private Date postAddedDate;

//    User creating the post
//    Many Post : One User
    @ManyToOne
    private User user;

//    Category under which the post exist
//    Many Post : One Category
    @ManyToOne
    private Category category;
}
