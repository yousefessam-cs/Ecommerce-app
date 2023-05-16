package com.example.ecommerce.dto.category;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBasicInfoDto {


    @NotNull(message = "category id must be included")
    private Long categoryId;
    @NotBlank(message = "category name must be included")
    private String categoryName;
}
