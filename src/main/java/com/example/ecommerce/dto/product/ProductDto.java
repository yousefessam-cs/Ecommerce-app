package com.example.ecommerce.dto.product;


import com.example.ecommerce.dto.category.CategoryBasicInfoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotNull(message = "category id must be included")
    private Long productId;
    @NotBlank(message = "product name must be included")
    private String productName;
    @NotNull(message = "product quantity must be included")
    @Min(value =0, message = "product quantity must be positive value" )
    private Integer productQuantity;
    @NotNull(message = "product price must be included")
    @Min(value =0, message = "category price must be positive value" )
    private Double price;
    @Valid
    private CategoryBasicInfoDto productCategory;
}
