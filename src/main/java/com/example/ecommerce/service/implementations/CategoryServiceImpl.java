package com.example.ecommerce.service.implementations;

import com.example.ecommerce.dto.category.CategoryBasicInfoDto;
import com.example.ecommerce.dto.category.CategoryCreateRequest;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exceptions.ResourceAlreadyExistsException;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.mapper.CategoryMapper;
import com.example.ecommerce.repository.CategoryRepo;
import com.example.ecommerce.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public CategoryDto add(CategoryCreateRequest categoryInfo)
    {
        if(categoryRepo.existsByName(categoryInfo.getCategoryName()))
            throw new ResourceAlreadyExistsException("Category with name = "+ categoryInfo.getCategoryName() + " already exists");


        Category category = new Category();
        category.setName(categoryInfo.getCategoryName());
        category.setActive(true);
        category.setProducts(new ArrayList<>());
        categoryRepo.save(category);

        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Override
    public void update(CategoryDto categoryDto)
    {
        if(!categoryRepo.existsById(categoryDto.getCategoryId()))
            throw new ResourceNotFoundException("Category with id = "+ categoryDto.getCategoryId() + " does not exist");
        Category category = categoryMapper.mapCategoryDtoToCategory(categoryDto);

        categoryRepo.save(category);


    }

    @Override
    public void activate(Long categoryId)
    {
        Category category = findCategoryById(categoryId);
        category.setActive(true);
        categoryRepo.save(category);



    }

    @Override
    public void deactivate(Long categoryId)
    {
        Category category = findCategoryById(categoryId);
        category.setActive(false);
        categoryRepo.save(category);
    }

    @Override
    public Page<CategoryBasicInfoDto> listAll(Pageable pageRequestInfo) {

        return categoryMapper.mapCategoryPageToCategoryInfoPage(categoryRepo.getAllByActive(true,pageRequestInfo));
    }


    private Category findCategoryById(Long categoryId)
    {
        return categoryRepo.findById(categoryId)
                .orElseThrow( ()->  new ResourceNotFoundException("Category with id = "+ categoryId + " does not exist"));

    }



}
