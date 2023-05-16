package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.category.CategoryBasicInfoDto;
import com.example.ecommerce.dto.product.ProductCreateRequest;
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
public interface ProductMapper
{
    @Mapping(target = "productId",source = "id")
    @Mapping(target = "productName",source = "name")
    @Mapping(target = "productQuantity",source = "quantity")
    @Mapping(target = "productCategory",source = "category")
    ProductDto mapProductToProductDto(Product product);

    @Mapping(target = "id",source = "productId")
    @Mapping(target = "name",source = "productName")
    @Mapping(target = "quantity",source = "productQuantity")
    @Mapping(target = "category",source = "productCategory")
    Product mapProductDtoToProduct(ProductDto productDto);


    default Page<ProductDto> mapProductPageToProductDtoPage(Page<Product> productPage)
    {
        List<ProductDto> list = productPage.getContent()
                .stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());
        return new PageImpl<ProductDto>(list,productPage.getPageable(),productPage.getTotalElements());
    }





    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    CategoryBasicInfoDto mapCategoryToCategoryInfoDto(Category category);
}
