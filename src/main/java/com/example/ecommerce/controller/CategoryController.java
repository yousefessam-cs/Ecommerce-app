package com.example.ecommerce.controller;

import com.example.ecommerce.dto.category.CategoryCreateRequest;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.ResponseDto;
import com.example.ecommerce.service.interfaces.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController
{
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseDto> addCategory(@RequestBody @Valid CategoryCreateRequest request)
    {
        CategoryDto categoryDto = categoryService.add(request);

        ResponseDto responseDto = new ResponseDto();

        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.CREATED);
        responseDto.setData(categoryDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateCategory(@RequestBody @Valid CategoryDto toBeUpdated)
    {
        categoryService.update(toBeUpdated);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);

        return new ResponseEntity<>( responseDto,HttpStatus.OK);
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<ResponseDto> activateCategory(@PathVariable Long id)
    {
        categoryService.activate(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);

        return new ResponseEntity<>( responseDto,HttpStatus.OK);

    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<ResponseDto> deactivateCategory(@PathVariable Long id)
    {
        categoryService.deactivate(id);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);

        return new ResponseEntity<>( responseDto,HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<ResponseDto> listAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size)
    {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(true);
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setData(categoryService.listAll(PageRequest.of(page,size)));
        return new ResponseEntity<>(responseDto,HttpStatus.OK);


    }



}
