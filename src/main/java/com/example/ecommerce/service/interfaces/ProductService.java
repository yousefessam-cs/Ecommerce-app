package com.example.ecommerce.service.interfaces;

import com.example.ecommerce.dto.product.ProductCreateRequest;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductFilterDto;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto add(ProductCreateRequest request);
    void update(ProductDto productDto);
    void activate(Long productId);
    void deactivate(Long productId);
    Page<ProductDto> listAll(Pageable pageRequestInfo);
    Page<ProductDto> filterProducts(ProductFilterDto productFilter,Pageable pageRequestInfo);




}
