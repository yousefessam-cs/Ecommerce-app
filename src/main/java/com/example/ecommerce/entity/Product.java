package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private boolean active;
    @ManyToOne
    private Category category;

}
