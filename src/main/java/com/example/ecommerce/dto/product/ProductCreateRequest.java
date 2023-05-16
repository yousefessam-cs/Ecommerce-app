package com.example.ecommerce.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    @NotBlank(message = "product name must be included")
    private String productName;
    @NotNull(message = "product quantity must be included")
    @Min(value =0, message = "product quantity must be positive value" )
    private Integer productQuantity;
    @NotNull(message = "product price must be included")
    @Min(value =0, message = "product price must be positive value" )
    private Double price;
    @NotNull(message = "product id must be included")
    private Long CategoryId;
}
