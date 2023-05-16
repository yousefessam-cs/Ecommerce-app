package com.example.ecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDto
{
    Double lowProductPrice;
    Double highProductPrice;
    Long productCategoryId;
}
