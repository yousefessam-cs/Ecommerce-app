package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private  String name;
    private boolean active = true;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;






}
