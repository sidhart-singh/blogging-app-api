package com.sidhart.singh.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private List<PostDTO> postDTOList;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private Integer totalPages;
    private boolean isLastPage;
}
