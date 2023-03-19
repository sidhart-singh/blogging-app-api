package com.sidhart.singh.blog.services.impl;

import com.sidhart.singh.blog.entities.Category;
import com.sidhart.singh.blog.exceptions.ResourceNotFoundException;
import com.sidhart.singh.blog.payloads.CategoryDTO;
import com.sidhart.singh.blog.repositories.CategoryRepo;
import com.sidhart.singh.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

//        save the updated category in the repo
        this.categoryRepo.save(category);
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

//        delete the category from the categoryRepo using categoryRepo predefined functions
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categoryList = this.categoryRepo.findAll();

        List<CategoryDTO> categoryDTOList = categoryList.stream()
                    .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                    .collect(Collectors.toList());

        return categoryDTOList;
    }
}
