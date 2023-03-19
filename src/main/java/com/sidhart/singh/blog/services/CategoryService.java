package com.sidhart.singh.blog.services;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.payloads.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

//    method declarations are public & abstract by default :
//    don't specify public access specifier in Interfaces
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDTO getCategory(Integer categoryId);

    List<CategoryDTO> getCategories ();
}
