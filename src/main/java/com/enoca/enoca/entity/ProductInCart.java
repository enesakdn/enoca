package com.enoca.enoca.entity;

import com.enoca.enoca.entity.Cart;
import com.enoca.enoca.entity.Product;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
public class ProductInCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int productCount;
}