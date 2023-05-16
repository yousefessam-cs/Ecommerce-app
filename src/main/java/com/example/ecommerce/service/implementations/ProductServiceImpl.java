package com.example.ecommerce.service.implementations;

import com.example.ecommerce.dto.product.ProductCreateRequest;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductFilterDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exceptions.ResourceAlreadyExistsException;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.repository.CategoryRepo;
import com.example.ecommerce.repository.ProductRepo;
import com.example.ecommerce.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductMapper productMapper;

    @Override
    public ProductDto add(ProductCreateRequest request)
    {
        Category category= categoryRepo.findById(request.getCategoryId()).
                orElseThrow(()-> new ResourceAlreadyExistsException("Category with id = "+ request.getCategoryId() + " does not exist"));
        Product product = new Product();
        product.setActive(true);
        product.setName(request.getProductName());
        product.setQuantity(request.getProductQuantity());
        product.setCategory(category);

        productRepo.save(product);

        return productMapper.mapProductToProductDto(product);
    }

    @Override
    public void update(ProductDto productDto)
    {
        if(!productRepo.existsById(productDto.getProductId()))
            throw new ResourceNotFoundException("product with id = "+ productDto.getProductId() + " does not exist");

        Product product  = productMapper.mapProductDtoToProduct(productDto);
        productRepo.save(product);

    }

    @Override
    public void activate(Long productId)
    {
        Product product = findProductById(productId);
        product.setActive(true);
        productRepo.save(product);

    }

    @Override
    public void deactivate(Long productId) {

        Product product = findProductById(productId);
        product.setActive(false);
        productRepo.save(product);

    }

    @Override
    public Page<ProductDto> listAll(Pageable pageRequestInfo)
    {
        Page<Product> productsPage = productRepo.getAllByActiveAndCategory_Active(true,true,pageRequestInfo);
        return productMapper.mapProductPageToProductDtoPage(productsPage);
    }

    @Override
    public Page<ProductDto> filterProducts(ProductFilterDto productFilter,Pageable pageRequestInfo)
    {
        Page<Product> productsPage = productRepo.getAllByCategoryAAndPriceRange(
                productFilter.getProductCategoryId(),productFilter.getLowProductPrice(),productFilter.getHighProductPrice(),pageRequestInfo);
        return productMapper.mapProductPageToProductDtoPage(productsPage);
    }


    private Product findProductById(Long productId)
    {
        return productRepo.findById(productId)
                .orElseThrow(()->  new ResourceNotFoundException("product with id = "+ productId + " does not exist"));
    }
}
