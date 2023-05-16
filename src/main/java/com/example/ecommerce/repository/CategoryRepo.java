package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category,Long>
{
    Page<Category> getAllByActive(boolean activeStatus, Pageable pageRequestInfo);
    boolean existsByName(String name);





}
