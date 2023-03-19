package com.sidhart.singh.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    @Size(min = 5, message = "Title must be greater than 5 characters.")
    @NotEmpty(message = "Title must not be empty.")
    private String categoryTitle;
    @NotEmpty
    @Size(max = 20, message = "Description must be greater than 20 characters.")
    private String categoryDescription;
}
