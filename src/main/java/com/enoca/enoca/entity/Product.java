package com.enoca.enoca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int productStockCount;

    private double productPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInCart> carts;
}