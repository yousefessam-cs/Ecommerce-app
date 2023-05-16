package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ResponseDto;
import com.example.ecommerce.dto.product.ProductCreateRequest;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductFilterDto;
import com.example.ecommerce.service.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseDto> addProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest)
    {
        ProductDto product = productService.add(productCreateRequest);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(product);
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.CREATED);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody @Valid ProductDto toBeUpdated)
    {
        productService.update(toBeUpdated);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);
        return new ResponseEntity<>( responseDto,HttpStatus.OK);
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<ResponseDto> activateProduct(@PathVariable Long id)
    {
        productService.activate(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);

        return new ResponseEntity<>( responseDto,HttpStatus.OK);

    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<ResponseDto> deactivateProduct(@PathVariable Long id)
    {
        productService.deactivate(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);

        return new ResponseEntity<>( responseDto,HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<ResponseDto> listAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size)
    {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setData(productService.listAll(PageRequest.of(page,size)));
        return new ResponseEntity<>(responseDto,HttpStatus.OK);


    }

    @GetMapping("/filter")
    public ResponseEntity<ResponseDto> filterProducts(@RequestBody @Valid ProductFilterDto filterDto,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size)
    {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setData(productService.filterProducts(filterDto,PageRequest.of(page,size)));
        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }





}
