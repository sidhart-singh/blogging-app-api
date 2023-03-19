package com.sidhart.singh.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
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


//    getting these form URL

//    User creating the post
//    Many Post : One User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    Category under which the post exist
//    Many Post : One Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
