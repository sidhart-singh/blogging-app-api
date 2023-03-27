package com.sidhart.singh.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private Date postAddedDate;

    private String postImage;

//    Must not user 'Category' & 'User': Instead user 'CategoryDTO' & 'UserDTO'
//    Infinite Recursion : Category contains List<Post>
//    CategoryDTO doesn't have any 'Post' field
    private CategoryDTO category;
    private UserDTO user;

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", postImage='" + postImage + '\'' +
                '}';
    }
}
