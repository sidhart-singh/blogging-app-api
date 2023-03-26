package com.sidhart.singh.blog.repositories;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.entities.Post;
import com.sidhart.singh.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
//    Derived Query Methods
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

//    custom method for search service :
//    generates a query with 'LIKE' clause with given field :
//    return list of posts with their title containing the required keywords
    List<Post> findByPostTitleContaining(String keywords);

//    custom method for search by title :
//    ':key' -> '%keyword%'
//    We need to use class name instead of table name and field names instead of column names in query string.
//    But if we are using property of nativeQuery=true we must use table and column names in query string.
    @Query("SELECT post FROM Post post WHERE post.postTitle LIKE :key")
    List<Post> searchByTitle(@Param("key") String title);
}
