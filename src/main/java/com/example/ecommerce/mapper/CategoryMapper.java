package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.category.CategoryBasicInfoDto;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    CategoryDto mapCategoryToCategoryDto(Category category);


    @Mapping(target = "id", source = "categoryId")
    @Mapping(target = "name", source = "categoryName")
    Category mapCategoryDtoToCategory(CategoryDto categoryDto);



    @Mapping(target = "id", source = "categoryId")
    @Mapping(target = "name", source = "categoryName")
    Category mapCategoryBasicInfoToCategory(CategoryBasicInfoDto categoryDto);



    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    CategoryBasicInfoDto mapCategoryToCategoryBasicInfoDto(Category category);

   default Page<CategoryBasicInfoDto> mapCategoryPageToCategoryInfoPage(Page<Category> categoryPage)
   {
       List<CategoryBasicInfoDto> list = categoryPage.getContent()
                                                .stream()
                                                .map(this::mapCategoryToCategoryBasicInfoDto)
                                                .collect(Collectors.toList());
       return new PageImpl<CategoryBasicInfoDto>(list,categoryPage.getPageable(),categoryPage.getTotalElements());
   }

    @Mapping(target = "productId",source = "id")
    @Mapping(target = "productName",source = "name")
    @Mapping(target = "productQuantity",source = "quantity")
    @Mapping(target = "productCategory",source = "category")
    ProductDto mapProductToProductDto(Product product);


}
