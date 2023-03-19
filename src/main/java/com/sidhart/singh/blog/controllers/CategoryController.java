package com.sidhart.singh.blog.controllers;

import com.sidhart.singh.blog.payloads.ApiResponse;
import com.sidhart.singh.blog.payloads.CategoryDTO;
import com.sidhart.singh.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

//    CREATE
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategoryDTO = this.categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

//    UPDATE
//    Requires both CategoryDTO and CategoryId
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
        CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO, categoryId);

        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
        CategoryDTO categoryDTO = this.categoryService.getCategory(categoryId);

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<CategoryDTO> categoryDTOList = this.categoryService.getCategories();

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }
}
