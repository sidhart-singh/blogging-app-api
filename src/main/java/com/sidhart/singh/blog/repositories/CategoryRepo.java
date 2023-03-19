package com.sidhart.singh.blog.repositories;

import com.sidhart.singh.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
