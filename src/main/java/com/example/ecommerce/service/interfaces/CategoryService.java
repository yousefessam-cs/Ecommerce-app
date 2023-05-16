package com.example.ecommerce.service.interfaces;

import com.example.ecommerce.dto.category.CategoryCreateRequest;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.category.CategoryBasicInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDto add(CategoryCreateRequest request);
    void update(CategoryDto categoryDto);
    void activate(Long categoryId);
    void deactivate(Long categoryId);
    Page<CategoryBasicInfoDto> listAll(Pageable pageRequestInfo);

}
