package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product,Long>
{

    Page<Product> getAllByActiveAndCategory_Active(boolean productActiveStatus,boolean categoryActiveStatus, Pageable pageable);

    @Query("select p from Product p where p.price >= coalesce(:lowProductPrice,p.price) and p.price <= coalesce(:highProductPrice,p.price) and p.category.id = coalesce(:categoryId,p.category.id) ")
    Page<Product> getAllByCategoryAAndPriceRange(@Param("categoryId") Long categoryId, @Param("lowProductPrice") Double lowProductPrice,@Param("highProductPrice") Double highProductPrice,Pageable pageable);

}
