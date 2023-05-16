package com.example.ecommerce.dto.category;


import com.example.ecommerce.dto.product.ProductDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotNull(message = "category id must be included")
    private Long categoryId;
    @NotBlank(message = "category name must be included")
    private String categoryName;

    private List<ProductDto> products;


}
